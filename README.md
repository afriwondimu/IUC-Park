# IUC Park Smart Motor Coupon Parking System

The **IUC Park Smart Motor Coupon Parking System** is a Java-based console application developed for **Infolink University College** to modernize motorbike parking management. Designed to replace inefficient handwritten paper records, this system streamlines check-in and check-out processes, ensures reliable data storage, and provides exportable reports for tracking. Built with a modular architecture, it offers a scalable and maintainable solution tailored to the university's parking needs.

---

## 📌 Introduction

Before this system, Infolink University College relied on handwritten paper records for parking management, which caused significant issues:

❌ **Slow Check-Outs**: Manual processes led to long delays and traffic jams at exits.  
❌ **Data Loss**: Paper records were prone to misplacement, with no reliable backups.  
❌ **Errors**: Handwritten time entries often resulted in inaccuracies.  

✅ **The Smart Motor Coupon Parking System Solves These By**:  
⚡ **Fast Digital Check-In/Check-Out**: Automates processes for quick and efficient parking management.  
💾 **Data Saved in `vehicles.csv`**: Ensures persistent, accurate storage of parking records.  
📄 **Records Exported as `.txt` Files**: Provides detailed reports for tracking and analysis.  

This project was developed to enhance operational efficiency and reliability for the university's parking facilities.

---

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Sample Output](#sample-output)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Check-In System**: Assigns a unique coupon code (1-300) to a motorbike, recording its plate number and check-in time.
- **Check-Out System**: Marks a motorbike as checked out, freeing the coupon code for reuse.
- **Record Export**: Generates detailed reports for a specific date, optionally filtered by plate number, saved as text files.
- **Persistent Storage**: Saves parking records to a `vehicles.csv` file for data persistence.
- **Authentication**: Secures access with a username (`iuc`) and password (`123`).
- **Modular Design**: Organized into packages (`model`, `manager`, `service`) for clean code and easy maintenance.
- **Error Handling**: Validates inputs (coupon codes, plate numbers, dates) with clear error messages.

---

## Project Structure

```
parkiuc/
└── src/
    ├── iucpark/
    │   ├── model/
    │   │   ├── Vehicle.java        # Abstract class for vehicles
    │   │   └── Motorbike.java      # Concrete motorbike class
    │   ├── manager/
    │   │   ├── RecordManager.java  # Interface for record management
    │   │   └── Iuc_Park_Main.java  # Main system logic and menu
    │   └── service/
    │       ├── CheckInService.java # Handles check-in operations
    │       ├── CheckOutService.java# Handles check-out operations
    │       ├── ExportService.java  # Handles record exports
    │       ├── FileService.java    # Manages CSV file operations
    │       └── AuthService.java    # Handles user authentication
    └── App.java                    # Program entry point
```

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **VS Code** (recommended) with the Java Extension Pack installed.
- **Operating System**: Windows, macOS, or Linux.
- **Command Line Interface**: For compiling and running the application.

---

## Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/afriwondimu/IUC-Park.git
   cd IUC-Park
   ```

2. **Verify Directory Structure**:

   - Ensure all Java files are in `src/iucpark/` as shown in the Project Structure.
   - The `src` directory should be the source root.

3. **Configure VS Code** (if using):

   - Open the project in VS Code (`File > Open Folder > D:/Java/parkiuc`).
   - Create or update `.vscode/settings.json`:

     ```json
     {
       "java.project.sourcePaths": ["src"],
       "java.project.outputPath": "bin"
     }
     ```

   - Install the **Java Extension Pack** via the Extensions view (`Ctrl+Shift+X`).

4. **Compile the Project**:

   - Open a terminal in the project root (`D:/Java/parkiuc`).
   - Compile all Java files:

     ```bash
     javac -d bin src/App.java src/iucpark/**/*.java
     ```

5. **Run the Application**:

   - Execute the program:

     ```bash
     java -cp bin App
     ```

---

## Usage

1. **Launch the Program**:

   - Run the application as described above.
   - Log in with the default credentials:
     - Username: `iuc`
     - Password: `123`

2. **Navigate the Menu**:

   - Choose from three options:
     1. **Check In**: Enter a coupon code (1-300) and plate number to record a motorbike's entry.
     2. **Check Out**: Enter a coupon code to mark a motorbike as checked out.
     3. **Export Records**: Enter a date (yyyy-MM-dd) and optional plate number to export records to a text file.

3. **Data Storage**:

   - Records are saved in `vehicles.csv` in the project root.
   - Exported reports are saved as `records_[date].txt` or `records_[date]_[plate].txt`.

---

## Sample Output

**Check-In Example** (at 1:25 PM EAT, May 27, 2025):

```
Username: iuc
Password: 123
Smart Motor Coupon System
1. Check In
2. Check Out
3. Export Records
Choose: 1
Coupon (1-300): 123
Plate: 4567
Check-in: Coupon 123, Plate 4567
```

**vehicles.csv**:

```
coupon_code,plate_number,check_in_time,check_out_time
123,4567,2025-05-27 13:25:00,
```

**Check-Out Example**:

```
Choose: 2
Coupon (1-300): 123
Plate: 4567
Confirm (y/n): y
Check-out: Coupon: 123, Plate: 4567, Check-in: 2025-05-27 13:25:00, Check-out: 2025-05-27 13:30:00
```

**Export Example**:

```
Choose: 3
Enter date (yyyy-MM-dd): 2025-05-27
Enter plate number (or press Enter for all): 
Exported to records_2025-05-27.txt
```

**records_2025-05-27.txt**:

```
+-------+--------+---------------------+---------------------+
| Plate | Coupon | Check-in            | Check-out           |
+-------+--------+---------------------+---------------------+
| 4567  | 123    | 2025-05-27 13:25:00 | 2025-05-27 13:30:00 |
+-------+--------+---------------------+---------------------+
```

---

## Contributing

Contributions are welcome! To contribute:

1. **Fork the Repository**:

   - Click the "Fork" button on GitHub.

2. **Create a Branch**:

   ```bash
   git checkout -b feature/your-feature
   ```

3. **Make Changes**:

   - Follow the existing code style and package structure.
   - Add tests or documentation as needed.

4. **Commit and Push**:

   ```bash
   git commit -m "Add your feature"
   git push origin feature/your-feature
   ```

5. **Open a Pull Request**:

   - Describe your changes in the PR description.
   - Link any related issues.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

*Developed by [Afri Wondimu] for Infolink University College to revolutionize parking management.*