OpenLeaf 📖
OpenLeaf is a modern, lightweight Android book reader built with Jetpack Compose. It allows users to browse millions of titles via the Open Library API and read public domain classics with high-quality formatting provided by Standard Ebooks.

🚀 Key Features
Global Book Search: Powered by the Open Library API to find almost any book.

Native Reading Experience: Fetches raw HTML from Standard Ebooks and parses it into a native, paginated reader.

Dynamic Progress Tracking: Real-time progress bar and chapter counting as you read.

Clean UI/UX: Built entirely with Jetpack Compose for a smooth, reactive interface.

🛠 Tech Stack
Language: Kotlin

UI: Jetpack Compose (Material 3)

Networking: Retrofit & OkHttp

Dependency Injection: Hilt

Architecture: MVVM (Model-View-ViewModel)

Image Loading: Coil

📂 Branch Information
[!IMPORTANT]
The develop branch contains the fully implemented version of the project, including the latest bug fixes for the Standard Ebooks parser, the full-screen reader UI, and the navigation logic.

📸 Screenshots
<img width="1080" height="2400" alt="Screenshot_20260227_123339" src="https://github.com/user-attachments/assets/192bd95e-ccd0-4bfb-a2bc-704ef837b9a5" />
<img width="1080" height="2400" alt="Screenshot_20260227_123310" src="https://github.com/user-attachments/assets/bed929b0-f870-4b9b-a02c-03f02e7c8faf" />

⚙️ Installation
Clone the repository:

Bash
git clone https://github.com/your-username/OpenLeaf.git
Switch to the develop branch:

Bash
git checkout develop
Open the project in Android Studio Jellyfish or later.

Build and run on an emulator or physical device (API 24+)
