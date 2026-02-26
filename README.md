OpenLeaf: A Modern Jetpack Compose E-Reader
OpenLeaf is a high-performance Android e-reading application built using Jetpack Compose and Material 3. It bridges the gap between the Open Library API and Standard Ebooks, providing a seamless transition from book discovery to a beautiful, native reading experience.

Features
Discovery: Browse thousands of titles from the Open Library "Novela Juvenil" subject catalog.

Native Reader: Unlike standard apps that use WebViews, OpenLeaf parses raw HTML into native text for a smooth, lag-free reading experience.

Page-Swipe Navigation: Implements HorizontalPager to simulate a traditional book feel with modern gesture-based navigation.

Clean Typography: Leverages Android's HtmlCompat to preserve the high-quality formatting provided by Standard Ebooks.

Public Domain Focused: Automatically targets high-quality, free editions of literary classics.

Tech Stack
UI: Jetpack Compose (100% Kotlin)

Concurrency: Kotlin Coroutines & Flow

DI: Hilt (Dagger)

Networking: Retrofit with Scalars and Gson Converters

Image Loading: Coil (Compose-optimized)

Architecture: MVVM (Model-View-ViewModel) with a clean Repository pattern

How it Works
Metadata: Fetches book details and cover art from the Open Library API.

String Processing: Cleans and formats Author/Title strings into URL-safe slugs.

Parsing: Downloads single-page HTML from Standard Ebooks and splits the content into swipeable chapters based on <section> markers.

Rendering: Displays content in a native TextView inside a HorizontalPager for optimal performance.

Quick Setup for Developers
Clone the repo: git clone [https://github.com/Akhils25/Online-Book-Reader.git]

Open in Android Studio Hedgehog or newer.

Ensure Hilt and Kapt plugins are enabled in your build.gradle.

Run on an emulator or physical device with Internet access.
