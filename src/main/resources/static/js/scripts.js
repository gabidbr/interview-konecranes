document.addEventListener("DOMContentLoaded", function () {
    const track = document.getElementById('track');
    const controls = document.getElementById('controls');
    const vehicles = track.getElementsByClassName('vehicle');

    setInterval(() => {
        for (let vehicle of vehicles) {
            const vehicleId = vehicle.dataset.id;
            fetch(`/vehicles/${vehicleId}`)
                .then(response => response.json())
                .then(data => {
                    vehicle.style.left = data.x + 'px';
                    vehicle.style.top = data.y + 'px';

                    if (!document.getElementById('control-' + vehicleId)) {
                        const controlSection = document.createElement('img');
                        controlSection.id = 'control-' + vehicleId;
                        controlSection.className = 'control-section';

                        const label = document.createElement('span');
                        label.innerHTML = 'Vehicle ' + vehicleId + ': ';
                        label.className = 'vehicle-label';
                        controlSection.appendChild(label);

                        const directions = ['UP', 'DOWN', 'LEFT', 'RIGHT', 'STOP'];
                        directions.forEach(dir => {
                            const button = document.createElement('button');
                            button.innerHTML = dir.toLowerCase();
                            button.className = 'control-button';
                            button.addEventListener('click', () => {
                                if (dir === 'STOP') {
                                    const url = `/vehicles/${vehicleId}/stop`;
                                    const requestOptions = {
                                        method: 'POST'
                                    };
                                    fetch(url, requestOptions)
                                        .then(response => console.log(`Vehicle ${vehicleId} stopped`))
                                        .catch(error => console.error('Error:', error));
                                } else {
                                    const url = `/vehicles/${vehicleId}/direction`;
                                    const requestOptions = {
                                        method: 'PUT',
                                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                                        body: `direction=${dir}`
                                    };
                                    fetch(url, requestOptions)
                                        .then(response => console.log(`Vehicle ${vehicleId} moving ${dir}`))
                                        .catch(error => console.error('Error:', error));
                                }
                            });
                            controlSection.appendChild(button);
                        });

                        controls.appendChild(controlSection);
                    }
                });
        }
    }, 500);
});
