# Landlord Application – Development Documentation

**Version:** 1.0  
**Date:** 2025-09-16  
**Author:** Sumant Hosmane

## 1. Introduction

### 1.1 Purpose

The Landlord Application is designed to help farm landlords track finances and operations associated with farming. The application will manage expenditures, labour payments, crop tracking, revenue, and unsold produce. It provides clear insights into profitability, work scheduling, and cash flow to support better decision-making.

### 1.2 Scope

The app will be built as an Android mobile application using Java/Kotlin (prefer Kotlin for modern practices). Backend and data management will be powered by Firebase (Authentication, Firestore Database, Cloud Storage, Analytics). Future support for reporting, dashboards, and export functionality will also be included.

## 2. Functional Requirements

### 2.1 User Management

#### Authentication
- Login/Signup (Firebase Authentication – currently implemented with Mobile Number/Password authentication)
- Plans to add Google authentication in future iterations
- Role-based access: Landlord, Labourer, Business Partner

#### User Profiles
- Basic details (name, contact, role)
- Bank account details (optional for direct payouts)

### 2.2 Farm Operations

#### Crop Lifecycle Tracking
- Sowing
- Plowing
- Harvesting
- Reaping
- Seasonal & daily cycle monitoring

#### Crop Metadata
- Type
- Quantity
- Quality
- Spoilage
- Sold vs. Unsold inventory

#### Storage Management
- Stored crops
- Barn/warehouse info

### 2.3 Financial Tracking

#### Expenses
- Landlord expenses (fertilizers, seeds, machinery)
- Labour payments (daily, weekly, monthly)
- Transportation and logistics (distance, vehicle type, charges)

#### Revenues
- Sale of crops
- Unsold crops (potential revenue)

#### Payments
- On-demand payouts
- Cash and online transactions
- Petty cash tracking
- Auto-generated statements

### 2.4 Labour Management
- Worker registry (labourer details)
- Work schedule (hours, shifts, full-day/half-day)
- Automated payroll calculations
- Performance/attendance records

### 2.5 Reporting & Analytics
- Profit & loss statements
- Expense vs. revenue charts
- Crop yield vs. sales analytics
- Export reports (PDF/Excel)

## 3. Non-Functional Requirements

- **Scalability:** Firebase backend scales automatically
- **Security:** Firebase Auth + Firestore rules for access control
- **Performance:** App must handle 10,000+ records without degradation
- **Availability:** Offline mode (local caching with Firebase Sync)
- **Maintainability:** Modularized codebase with MVVM architecture

## 4. System Design

### 4.1 Architecture

#### Client App (Android)
- Built in Kotlin (latest stable version)
- MVVM pattern + Jetpack components
- UI with Material 3 (Jetpack Compose recommended)

#### Backend
- Firebase Authentication
- Firebase Firestore (NoSQL database)
- Firebase Cloud Storage (for receipts, crop images)
- Firebase Cloud Functions (business logic automation)

#### Integration
- Google Calendar API (for schedules/reminders)
- Razorpay/UPI integration (for online payments)

### 4.2 Data Model (Firestore)

#### Collections

**Users**
- id
- name
- role
- contact
- bankDetails

**Crops**
- id
- name
- season
- quantity
- quality
- status (sold/unsold)

**Labourers**
- id
- name
- wageType (daily/monthly)
- schedule
- payments

**Transactions**
- id
- type (income/expense)
- amount
- date
- category

**Storage**
- id
- location
- capacity
- currentStock

**Reports**
- id
- timeframe
- summaryData
- generatedFileLink

## 5. UI/UX Design

### Core Screens

#### Login / Signup
- Mobile number input with phone icon
- Password input with visibility toggle and lock icon
- Login button
- Sign up prompt for new users
- Material Design 3 components
- Error handling with validation messages

#### Dashboard
- Financial summary (income, expenses, net profit)
- Crop status (sold vs. unsold)

#### Crops Management
- Add/Edit crops with season, type, quantity
- Track growth cycle (sowing → harvesting → selling)

#### Labour Management
- Add workers, assign shifts
- Track payments (due, paid)

#### Transactions
- Record expenditure and revenue
- Categorize payments

#### Reports
- Charts for expenses vs. revenue
- Downloadable reports (PDF/Excel)

#### Storage Management
- View warehouses/barns
- Track stock levels and spoilage

## 6. Technology Stack

### Frontend (Mobile App)
- Kotlin (latest stable, 2.x if available in 2025)
- Android Studio Ladybug (latest)
- Jetpack Compose + Material 3
- MVVM Architecture

### Backend & Database
- Firebase Firestore (NoSQL, scalable)
- Firebase Authentication
- Firebase Cloud Functions (Node.js 20 LTS)
- Firebase Storage

### Other Tools
- Firebase Analytics & Crashlytics
- GitHub for version control
- Gradle (latest stable)

## 7. Future Enhancements
- AI-driven crop yield prediction (TensorFlow Lite integration)
- Integration with UPI Payment Gateways
- Multi-language support (English, Tamil, Hindi)
- SMS/WhatsApp notifications
- Web dashboard for large-scale landlords

## 8. Deployment Strategy
- Development → Staging → Production (Firebase environments)
- Play Store deployment with internal testers (Firebase App Distribution)
- Continuous Integration using GitHub Actions + Gradle Build

## 9. Development Setup and Testing

### 9.1 Prerequisites
- Android Studio Ladybug (2023.2.1) or later
- JDK 17 (required for Gradle configuration)
- Android SDK with minimum API level 24
- Firebase project setup and configuration
- Gradle 8.13 or later (for compatibility)

### 9.2 First Time Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/username/Landlord.git
   ```
2. Open project in Android Studio
3. Create a Firebase project at console.firebase.google.com
4. Add Android app with package name "com.landlord.app"
5. Download google-services.json and place it in the app/ directory
6. Ensure Gradle JDK is set to version 17 in Android Studio settings
7. Sync project with Gradle files

### 9.3 Project Configuration
- Gradle version: 8.13
- Android Gradle Plugin: 8.2.0
- Kotlin version: 1.9.20
- Minimum SDK: 24
- Target SDK: 34
- Firebase BoM: 32.3.1
- JDK: 17

### 9.4 Important Gradle Configurations

The following configurations in gradle.properties are crucial for proper build:

```properties
# JDK Settings
org.gradle.java.home=C:\\Program Files\\Java\\jdk-17
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8 --add-exports=java.base/sun.nio.ch=ALL-UNNAMED \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
    --add-opens=java.base/java.io=ALL-UNNAMED \
    --add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED

# Android Studio settings
android.useAndroidX=true
android.enableJetifier=true
```

These settings ensure:
- Correct JDK version is used
- Proper memory allocation for builds
- Required Java module exports and opens
- AndroidX compatibility
- Jetifier for legacy support

### 9.5 Building and Running
1. Connect an Android device (or use emulator)
   - Enable USB debugging on physical device
   - For emulator, create a device with API level 24+
2. In Android Studio:
   - Select device from the target device dropdown
   - Click "Run" (green play button) or press Shift+F10
3. First build may take several minutes
4. App will install and launch automatically

### 9.6 Testing Login
1. Launch app on device/emulator
2. Enter a 10-digit mobile number
3. Enter password (minimum 6 characters)
4. Tap Login button
5. Verify error handling by:
   - Entering invalid phone number
   - Entering short password
   - Testing empty fields

## 10. Conclusion

This documentation provides a roadmap for building the Landlord Finance Tracking Application. The app leverages modern Android (Kotlin, Jetpack Compose) with Firebase's scalable backend. It will serve as a robust platform for landlords to track expenses, labour, crops, and revenue, ensuring digital financial control over farming activities.
