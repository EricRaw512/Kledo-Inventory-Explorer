# Kledo Invetory Explorer

> Kledo Invetory Explorer is a user-friendly application designed to provide a comprehensive view of product inventory and warehouse information directly from the Kledo app. This tool is specifically tailored for viewing product details, including quantity and product photos, accessible from both mobile phones and PCs.

![alt text](https://github.com/EricRaw512/image/blob/main/KledoInvetoryExplorer.png?raw=true)

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/EricRaw512/KledoInvetoryExplrer.git
```

**2. Create MySQL database**

```bash
create database kledo_inventory_explorer
```

**3. Configure enviroment variables**

+ open `src/main/resources/application.yml`
+ set environment variables for JDBC `dbURL`, `dbUsername`, `dbPassword`

**4. Run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>

**5. Close the app and run the sql**

- After that run MySQL script to create admin account `src/main/resources/kledo_Invetory_explorer.sql`

**6. Login to admin account**

+ username: `admin`
+ password: `qwerty123`
