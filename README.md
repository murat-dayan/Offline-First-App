# Offline News App

This app is a modern Android application that works both online and offline. It leverages the MVI (Model-View-Intent) architecture and incorporates popular libraries such as Room, Retrofit, and Koin.
<div align="center">
<img src="https://github.com/user-attachments/assets/d248e0cc-d5f6-447a-869e-2ae1bfad2b00" alt="project-screenshot" width="300" height="800/">
</div>

## Features
- **Online and Offline Functionality:** Use the app even without an internet connection.
- **MVI Architecture:** Ensures easy management, testability, and maintainability.
- **Data Storage:** Local database management with Room.
- **API Communication:** Network calls with Retrofit.
- **Dependency Injection:** Efficient dependency management with Koin.

## Requirements
- MinSDK 24, Android 7.0 Nougat or above
- Internet connection (for online features)
- An API key (explained below)

## Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/murat-dayan/Offline-News-App.git
    cd OfflineNewsApp
    ```

2. Add your API key:
    - Open the project and add the following line to your `local.properties` file:
      ```properties
      API_KEY=your_api_key_here
      ```

4. Run the project:
    - Open the project in Android Studio and run it on your device or emulator.

## Usage
The application can operate in both offline and online modes. In offline mode, it reads and processes data from the local database. In online mode, it fetches the most recent data from the server and saves it to the local database.

## Technologies Used
- **Kotlin:** A modern and powerful programming language.
- **Room:** An advanced ORM for SQLite databases.
- **Retrofit:** A library for working with RESTful APIs.
- **Koin:** A simple and effective dependency injection framework.

## Contributing
Contributions are welcome! To contribute, please fork the repository and create a pull request. We appreciate all contributions!

1. Fork the repository
2. Create a new feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact
For questions regarding the project, you can contact [Me](https://github.com/murat-dayan).

---

Feel free to adjust this template to better fit your project details and preferences. If you need any further assistance, I'm here to help! Happy coding!
