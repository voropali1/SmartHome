# Smart Home Simulation

## 📌 Description

This project is a Smart Home simulation developed using various design patterns. The simulation models household activities, device usage, energy consumption, and interactions between residents, pets, and appliances.

## 🚀 Features

- 🏠 **Simulated home environment**: House, rooms, floors, sensors, appliances, residents, vehicles, and pets.

- ⚡ **Energy consumption tracking**: Devices record energy, gas, and water consumption.

- ⚙️ **Event-driven system**: Randomly generated events trigger appropriate responses from residents or devices.

- 📊 **Activity logging**: Tracks activities and usage patterns of residents and pets.

- 📑 **Reports generation**: Configuration reports, event logs, usage reports, and consumption reports.

- 🛠 **Error handling**: Devices break over time and require repair or replacement.

## 🏗 Implemented Design Patterns

- **Chain of Responsibility**: Events are processed using handlers.

- **Observer**: Time-dependent entities are monitored by a time component.

- **Builder**: Used for setting up the home configuration.

- **Factory Method**: Creates devices, appliances, and residents in collaboration with the builder.

- **Abstract Factory**: Generates different sensor types (internal and external, though external sensors are not yet implemented).

- **Proxy**: Adds a control layer for simulation start-up.

- **Singleton**: Manages core system state and controls.

- **State Machine**: Represents different states of residents and devices.

## 📂 Reports & Logs

The system generates multiple reports:

🏠 **HouseConfigurationReport** – Shows home configuration hierarchy and residents.

![1](diagrams_and_description/52.png)

⚠️ **EventReport** – Groups events by type, source, and handlers.

![1](diagrams_and_description/50.png)

🏃 **ActivityAndUsageReport** – Logs resident and pet activities and device usage.

![1](diagrams_and_description/48.png)

💰 **ConsumptionReport** – Tracks resource usage (electricity, gas, water) with cost estimates.

![1](diagrams_and_description/49.png)

## 📌 Simulation logs

![1](diagrams_and_description/46!(1).png)
![1](diagrams_and_description/40!(2).png)
![1](diagrams_and_description/47!(23).png)
![1](diagrams_and_description/45!(42).png)

## 📌 Use Case diagram

![1](diagrams_and_description/UseCase.png)

## 📌 Initial UML diagram

![1](diagrams_and_description/ClassDiagram.png)

## 📌 Implemented UML diagram

![1](diagrams_and_description/classDiagram2.png)

## ▶️ How to Run

1. **Clone the repository**  
   Clone the repository to your local machine using **IntelliJ IDEA**:
   - Go to **File** → **New** → **Project from Version Control**, and paste the repository link: `https://github.com/voropali1/smart_home.git`.

2. **Run the project**  
   To run the project:
   - In **IntelliJ IDEA**, find the **Main** class, then click the **Run** button.

3. **View logs and reports**  
   - **Logs** will be displayed in the **Run** panel at the bottom of the IntelliJ IDEA window.
   - **Reports** can be found in the `smart_home` folder, located at the bottom of your project directory.


## 📌 Future Improvements

✅ Implement external sensors

✅ Improve the graphical representation of reports

✅ Add more device types and improve device integration


### Developed with ❤️ by Alina Voropaeva




