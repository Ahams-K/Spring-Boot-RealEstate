-- Insert into Agent table with UUIDs
INSERT INTO Agent (AGENT_ID, AGENT_NAME, CONTACT_INFO, LICENCE_NUMBER, EMAIL,AGENCY_ID)
VALUES (RANDOM_UUID(), 'Kingsley', '+32465787555', '3A3-NWA', 'kingley.ahams@student.kdg.be', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Premium Realty')),
       (RANDOM_UUID(), 'Lesley', '+32843992200', 'AAA-ZE5', 'Lesley.Tianga@student.kdg.be',(SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Budget Homes')),
       (RANDOM_UUID(), 'Fortune', '+3274399307', 'YUB-456', 'Fortune@gmail.com',(SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'City plus')),
       (RANDOM_UUID(), 'Aisosa', '+3245676788', 'ZES-2RE', 'Aisosa@gmail.com',(SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Immovlan')),
       (RANDOM_UUID(), 'Maimuna', '+32466789704', 'RXE-BE1', 'Maimuna@gmail.com',(SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Immobob')),
       (RANDOM_UUID(), 'Alex', '+3245676788', 'NIG-IS6', 'Alex@gmail.com',(SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Premium Realty')),
       (RANDOM_UUID(), 'Elijah', '+32499876543', 'LOP-11X', 'Elijah@student.kdg.be', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Premium Realty')),
       (RANDOM_UUID(), 'Sophia', '+32456712345', 'MNO-45Z', 'Sophia@gmail.com', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Budget Homes')),
       (RANDOM_UUID(), 'Olivia', '+32465873901', 'QRS-88W', 'Olivia@gmail.com', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'City plus')),
       (RANDOM_UUID(), 'Ethan', '+32467238945', 'TUV-34R', 'Ethan@student.kdg.be', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Immovlan')),
       (RANDOM_UUID(), 'Liam', '+32489123456', 'WXY-67P', 'Liam@gmail.com', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Immobob')),
       (RANDOM_UUID(), 'Charlotte', '+32456789012', 'ZAB-12Y', 'Charlotte@student.kdg.be', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Premium Realty')),
       (RANDOM_UUID(), 'Amara', '+32467812349', 'DEF-56T', 'Amara@gmail.com', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Budget Homes')),
       (RANDOM_UUID(), 'Nathan', '+32456987654', 'GHI-78M', 'Nathan@student.kdg.be', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'City plus')),
       (RANDOM_UUID(), 'Emma', '+32468754321', 'JKL-99N', 'Emma@gmail.com', (SELECT AGENCY_ID FROM RealEstateAgency WHERE AGENCY_NAME = 'Immovlan'));


-- Insert into Property table with UUIDs
INSERT INTO Property (PROPERTY_ID, PROPERTY_NAME, IMAGE, ADDRESS, PRICE, PROPERTY_TYPE, SIZE, PROPERTY_STATUS, NUMBER_OF_ROOMS, DATELISTED)
VALUES
    (RANDOM_UUID(), 'Modern Haven', '/images/properties/Modern Haven.jpg', 'Nationalstraat 1 box 2', 1400000.0, 'RESIDENTIAL', 3000.0, 'SOLD', 18, '2024-04-19'),
    (RANDOM_UUID(), 'Cityview Residence', '/images/properties/Cityview Residence.jpg', 'Steynstraat 1 box 2', 3000000.0, 'RESIDENTIAL', 7000.0, 'AVAILABLE', 10, '2025-01-01'),
    (RANDOM_UUID(), 'Business Plaza', '/images/properties/Business Plaza.jpg', 'Bolivarplaats 1 box 2', 15000000.0, 'COMMERCIAL', 8000.0, 'RENTED', 25, '2024-06-28'),
    (RANDOM_UUID(), 'Suburban Retreat', '/images/properties/Suburban Retreat.jpg', 'Boukunststraat 1 box 2', 1400000.0, 'RESIDENTIAL', 2000.0, 'LEASED', 8, '2024-07-12'),
    (RANDOM_UUID(), 'Weekend Escape', '/images/properties/Weekend Escape.jpg', 'Ruggeveld 1 box 2', 1400000.0, 'RECREATIONAL', 2000.0, 'AVAILABLE', 8, '2025-01-04'),
    (RANDOM_UUID(), 'Greenfield Factory', '/images/properties/Greenfield Factory.jpg', 'Groenplats 3 box 5', 6000000.0, 'INDUSTRIAL', 12000.0, 'AVAILABLE', 40, '2022-01-01'),
    (RANDOM_UUID(), 'Vacation Cabin', '/images/properties/Cabin.jpg', 'flyhigh 3 block 12', 450000.0, 'RESIDENTIAL', 4000.0, 'AVAILABLE', 4, '2022-01-01'),
    (RANDOM_UUID(), 'The View', '/images/properties/Penthouse.jpg', 'No 19 Stevens lane', 10000000.0, 'COMMERCIAL', 20000.0, 'AVAILABLE', 30, '2024-12-01'),
    (RANDOM_UUID(), 'Relaxation Haven', '/images/properties/Suite.jpg', 'Groenplats 10 box 9', 2500000.0, 'RESIDENTIAL', 7000.0, 'AVAILABLE', 7, '2024-11-11'),
    (RANDOM_UUID(), 'Prime Villa', '/images/properties/Apartment.jpg', 'NO 12 Main street', 5000000.0, 'RECREATIONAL', 12000.0, 'AVAILABLE', 8, '2024-12-25'),
    (RANDOM_UUID(), 'Sunny Haven', '/images/properties/Bungalow.jpg', 'N0 3 third street', 120000.0, 'RECREATIONAL', 1500, 'AVAILABLE', 2, '2024-12-27'),
    (RANDOM_UUID(), 'Aqua Oasis', '/images/properties/Aqua Oasis.jpg', 'No 35 Main Street', 3500000.0, 'INDUSTRIAL', 4500.0, 'AVAILABLE', 5, '2024-01-01'),
    (RANDOM_UUID(), 'Forest Edge Retreat', '/images/properties/Forest Retreat.jpg', 'No 3 Cresent close off road', 2000000.0, 'RECREATIONAL', 4000.0, 'AVAILABLE', 4, '2024-01-01'),
    (RANDOM_UUID(), 'Lake Villa', '/images/properties/Lake Villa.jpg', '12 Handel Second Street', 3000000.0, 'COMMERCIAL', 6000.0, 'AVAILABLE', 5, '2024-01-01'),
    (RANDOM_UUID(), 'Apartment Residence', '/images/properties/Apartment2.jpg', 'Groenplats 3 box 3', 200000.0, 'RESIDENTIAL', 1000.0, 'AVAILABLE', 3, '2024-11-01');



-- Insert into Owner table with UUIDs
INSERT INTO Owner (OWNER_ID, NAME, CONTACT_INFO, EMAIL, ADDRESS)
VALUES (RANDOM_UUID(), 'John Doe', '123-456-7890', 'johndoe@example.com', '123 Elm Street'),
       (RANDOM_UUID(), 'Jane Smith', '987-654-3210', 'janesmith@example.com', '456 Oak Avenue');

-- Insert into RealEstateAgency table with UUIDs
INSERT INTO RealEstateAgency (AGENCY_ID, AGENCY_NAME, ADDRESS, CONTACT_INFO, CITY, IMAGE)
VALUES (RANDOM_UUID(), 'Premium Realty', '789 High Street', 'premium@realty.com','Atlanta','/images/agencies/Premium Realty.jpg'),
       (RANDOM_UUID(), 'Budget Homes', '555 Low Street', 'budget@homes.com','New York','/images/agencies/Budget Homes.jpg'),
       (RANDOM_UUID(), 'City plus', '777 Middle Street', 'cityplus@plus.com','London','/images/agencies/City Plus.jpg'),
       (RANDOM_UUID(), 'Immovlan', '888 Main Street', 'immovlan@web.com','Brussels','/images/agencies/Immovlan.jpg'),
       (RANDOM_UUID(), 'Immobob', '999 Nationalstraat', 'immobob@web.com','Antwerp','/images/agencies/Land And Home.jpg');

-- Insert into Agent_Property table with UUIDs
INSERT INTO Agent_Property (AGENT_ID, PROPERTY_ID)
VALUES ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Kingsley'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Modern Haven')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Lesley'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Cityview Residence')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Fortune'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Business Plaza')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Aisosa'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Suburban Retreat')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Maimuna'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Weekend Escape')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Alex'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Greenfield Factory')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Elijah'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Vacation Cabin')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Sophia'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'The View')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Olivia'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Relaxation Haven')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Ethan'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Prime Villa')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Liam'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Sunny Haven')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Charlotte'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Modern Haven')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Amara'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Cityview Residence')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Nathan'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Business Plaza')),
       ((SELECT AGENT_ID FROM Agent WHERE AGENT_NAME = 'Emma'), (SELECT PROPERTY_ID FROM Property WHERE PROPERTY_NAME = 'Suburban Retreat'));



