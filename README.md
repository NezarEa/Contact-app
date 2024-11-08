
# Contacts App

The **Contacts App** is an Android application that allows users to view, manage, and interact with their contacts. It features multiple functionalities, including dark mode, language selection, and quick contact actions like calling or sending SMS messages. The app also supports recent calls display and provides a settings page to personalize the user experience.

## Features

- **Contact List**: View a list of contacts with their name, phone number, and an avatar representing the first letter of their name.
- **Dark Mode Toggle**: Enable or disable dark mode for better readability in low-light conditions.
- **Language Selection**: Switch between different languages (English, French, Arabic) for a localized experience.
- **Contact Actions**: Call or send SMS messages directly from the contact list.
- **Recent Calls**: Display recent calls with call details such as date, time, and phone number.
- **Settings**: Adjust dark mode and language preferences.

## Key Technologies

- **Kotlin**: The primary programming language used to develop the app.
- **RecyclerView**: Displays the list of contacts in a scrollable format.
- **AppCompatDelegate**: Manages dark mode functionality.
- **SharedPreferences**: Used to save user settings, including dark mode and language preferences.
- **Locale**: Supports multiple languages by changing the app's locale dynamically.
- **Permissions**: Requests permissions for accessing contacts, making calls, and sending SMS.

## Installation Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/NezarEa/Contact-app.git
   ```

2. **Open in Android Studio**:
   Launch Android Studio and open the project folder.

3. **Build and run the app**:
   Use the "Run" button in Android Studio to deploy the app to an emulator or device.

## Required Permissions

The app requires the following permissions:

- **CALL_PHONE**: To make phone calls directly from the contact list.
- **SEND_SMS**: To send SMS messages directly to a contact.
- **READ_CONTACTS**: To access the contacts stored on the device.
- **ACCESS_FINE_LOCATION** (optional): For location-related functionalities (if implemented).

Make sure these permissions are included in your **AndroidManifest.xml**:

```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## Language Support

The app currently supports the following languages:

- **English**
- **French**
- **Arabic**

## Usage

Once the app is running:

- **View Contacts**: The main screen will display a list of contacts with their names, phone numbers, and avatars.
- **Toggle Dark Mode**: Enable or disable dark mode from the settings page.
- **Change Language**: Select a language (English, French, or Arabic) from the settings page.
- **Contact Actions**: Tap on the options icon next to a contact to call or send an SMS.
- **View Recent Calls**: The recent calls fragment displays call details, including date, time, and phone number.

## Screenshots


<img src="https://github.com/user-attachments/assets/2f371365-f289-41c6-aa16-aafb9301aee9" alt="Screenshot 1" width="250"/>
<img src="https://github.com/user-attachments/assets/48effeeb-6387-4573-a2d9-116f0a7d1d26" alt="Screenshot 2" width="250"/>
<img src="https://github.com/user-attachments/assets/85d2355d-1265-4f88-b82f-52dc4506a148" alt="Screenshot 3" width="250"/>
<img src="https://github.com/user-attachments/assets/26709af1-66b4-4651-b2cb-4165b08490be" alt="Screenshot 3" width="250"/>

## License

This project is licensed under the MIT License. See the [NezarEa](https://github.com/NezarEa) file for more details.

## Contact

For any questions or suggestions, feel free to reach out to me at [nezarelayachi@gmail.com](mailto:nezarelayachi@gmail.com).

---

Enjoy exploring the Contacts App! 🎉
