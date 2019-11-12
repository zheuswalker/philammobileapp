-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 11, 2019 at 02:08 PM
-- Server version: 5.7.27-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `philam`
--

-- --------------------------------------------------------

--
-- Table structure for table `philam_client_credentials`
--

CREATE TABLE `philam_client_credentials` (
  `pcc_accountid` int(11) NOT NULL,
  `pcc_fullname` varchar(500) NOT NULL,
  `pcc_birthdate` varchar(250) NOT NULL,
  `pcc_address` varchar(1500) NOT NULL,
  `pcc_email` varchar(50) NOT NULL,
  `pcc_contact` varchar(50) NOT NULL,
  `pcc_password` varbinary(50) NOT NULL,
  `pcc_dateregistered` datetime DEFAULT CURRENT_TIMESTAMP,
  `pcc_contactoption` varchar(250) NOT NULL,
  `pcc_contacttime` varchar(5) NOT NULL,
  `pcc_newsletter` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `philam_client_credentials`
--

INSERT INTO `philam_client_credentials` (`pcc_accountid`, `pcc_fullname`, `pcc_birthdate`, `pcc_address`, `pcc_email`, `pcc_contact`, `pcc_password`, `pcc_dateregistered`, `pcc_contactoption`, `pcc_contacttime`, `pcc_newsletter`) VALUES
(3, 'John DoeDoe', 'Nov 9, 2019', 'Valle Verde Pasig', 'test@email.com', '+63912345678', 0x3566346463633362356161373635643631643833323764656238383263663939, '2019-11-08 11:10:27', 'email', 'Pm', 1),
(4, 'Keith Eyvan Alvior', 'Nov 9, 2019', 'Valle Verde Pasig', 'your@email.com', '+63912345678', 0x3566346463633362356161373635643631643833323764656238383263663939, '2019-11-09 13:43:53', 'email', 'Am', 1),
(9, 'Keith Eyvan Alvior', '', '', 'cloudfonethrill430dblue@gmail.com', '', 0x3764646633326531376136616335636530346138656362663738326361353039, '2019-11-09 21:54:49', 'email', 'AM', 1);

-- --------------------------------------------------------

--
-- Table structure for table `philam_products_offered`
--

CREATE TABLE `philam_products_offered` (
  `ppo_productid` int(11) NOT NULL,
  `ppo_productname` varchar(500) NOT NULL,
  `ppo_productequity` decimal(10,0) NOT NULL DEFAULT '0',
  `ppo_issueage` varchar(500) NOT NULL,
  `ppo_coverage` varchar(500) NOT NULL,
  `ppo_buyingoption` varchar(500) NOT NULL,
  `ppo_productdesc` varchar(5000) NOT NULL,
  `ppo_providents` varchar(5000) NOT NULL,
  `ppo_fineprint` varchar(5000) NOT NULL,
  `ppo_serviceid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `philam_products_offered`
--

INSERT INTO `philam_products_offered` (`ppo_productid`, `ppo_productname`, `ppo_productequity`, `ppo_issueage`, `ppo_coverage`, `ppo_buyingoption`, `ppo_productdesc`, `ppo_providents`, `ppo_fineprint`, `ppo_serviceid`) VALUES
(1, 'Life Smart ', '20000', '18-50 YEARS OLD ', '20 YEARS', 'PHILAM LIFE FINANCIAL ADVISOR', 'Making choices can be hard especially when you have loved ones who depend on you. You try to be careful because the future is always uncertain. You want to make smart choices so things can go on as planned.\n\nLife Smart gives you guaranteed life insurance coverage with accident and terminal illness benefits. At the end of your plan, you can get all your payments back plus a cash bonus.\n\nLife Smart is available for a minimum premium of PHP 20,000 a year.\n', '\r\nwhat does it provide?\r\n\r\n    GUARANTEED LIFE AND ACCIDENT INSURANCE\r\n\r\n    Leave guaranteed cash for your loved ones. This amount doubles if the loss is caused by an accident. You can have peace of mind knowing your loved ones can continue the life you planned for them.\r\n\r\n    COVERAGE FOR TERMINAL ILLNESS\r\n\r\n    You will get your life insurance coverage in advance if you get diagnosed with a terminal illness.\r\n\r\n    CASH BENEFIT AT THE END OF YOUR PLAN\r\n\r\n    If you continue your plan for 20 years and all payments are made, you will get your payments back. Plus, you can also get additional cash through yearly dividends and a loyalty bonus.', '      Vanguard is a whole life insurance plan that can include optional riders.     Vanguard can also give you non-guaranteed periodic dividends that may be used to boost protection benefits or can be paid out as cash for various financial needs.     The minimum premium required may increase depending on the occupational and medical ratings of the proposed insured.     The contents of this page are for illustrative purposes only. Actual terms and conditions of insurance coverage are found in the policy. In case of conflict, policy terms shall prevail.   ', 1),
(2, 'Vanguard 100 ', '22000', '0-70 YEARS OLD', 'UNTIL 100 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', '<p>Product Description </p>\n\nYou want protection for yourself and your loved ones as well as the freedom to choose the benefits to suit your needs and budget.\n\nWith the versatility of Philam Life’s Vanguard, you can enjoy a lifetime protection plan with benefits that are tailor-fit to your lifestyle, as well as all your special needs.\n', '\r\nwhat does this provide?\r\n\r\n    GUARANTEED LIFETIME PROTECTION\r\n\r\n    Leave a cash amount for your loved ones when you pass away so you can make sure that their needs are taken care of.\r\n\r\n    FLEXIBLE AND CUSTOMIZABLE COVERAGE\r\n\r\n    Have the flexibility to choose the amount of your coverage based on your needs.\r\n\r\n    AFFORDABLE AND EASY TO PAY\r\n\r\n    Get affordable life protection with a minimum coverage of PHP 150,000. Enjoy easy payment terms of 5, 10, or 20 years.\r\n\r\n', '  •Payments to be returned refers to the total Annual Premiums. The amount of total Annual Premiums returned may differ from the total amount of actual premiums paid due to rounding differences and modal factor, if any.  •The dividends and the loyalty bonus are not guaranteed and can be zero. The Loyalty Bonus, if any, would be equal to a percentage of the total annual dividends earned and will be given at the end of the plan.', 1),
(3, 'HEALTH INVEST', '15000', '18-65 YEARS OLD', 'UNTIL 75 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'While 97% of Filipinos prioritize safeguarding their and their family’s health over other concerns in life, only 16% say that they are financially prepared for serious health conditions. Those who are not prepared admit that they will be financially burdened and will resort to loaning money or selling property and assets to pay for their medical expenses.\r\n\r\nHealth Invest prepares you for health setbacks now and in the future. It provides comprehensive health benefits, including coverage for critical illnesses. It also has a fund for future healthcare needs.\r\n\r\nHealth Invest is available at a minimum premium of PHP 30,000 a year.', 'CRITICAL ILLNESS COVERAGE\r\nReceive cash if you get diagnosed with any of 56 covered major critical illness including cancer, heart attack, or stroke.\r\n\r\n \r\n\r\nVIEW LIST OF COVERED ILLNESSES \r\nACCIDENT COVERAGE\r\nGet additional cash to use for serious accidental injuries, disabilities or if you pass away due to an accident until age 72.\r\n\r\nWAIVER OF PREMIUMS\r\nContinue your plan without paying future premiums once diagnosed with a major critical illness or once you are totally and permanently disabled. \r\n\r\nGROWING HEALTH FUND\r\nMake your money grow through expertly-managed funds that give you an account value to use for healthcare and other costs in the future. \r\n\r\nLIFE INSURANCE COVERAGE\r\nLeave a cash amount for your loved ones when you pass away so you can make sure that their needs are taken care of. In case your health fund becomes higher than your life insurance coverage, your loved ones can get the higher amount instead.', 'Health Invest has a built-in Critical Illness, Waiver of Premium for Critical Illness (WPCI) and Accident and Health (A&H) riders.\r\nOnce a claim has been made for any of the 56 major critical illness, the Critical Illness rider will terminate.\r\nThe WPCI rider waives the basic premiums, regular top-ups and special top-ups in case of diagnosis of any of the 56 covered major critical illnesses which commenced at least 90 days after the effective date.\r\nPremium period is not guaranteed. In the future, if the account value becomes insufficient to pay for charges, it is possible that additional premiums (i.e. top-ups) will be required to continuously enjoy the benefits of this plan and its riders.\r\nPlease consult your Financial Advisor or refer to the policy contract for full details on the critical illness and hospital confinement benefit definitions, exclusions and limitations.\r\nThe amount of your account value will vary depending on fund performance which may be affected by market conditions.\r\nThe minimum premium required may increase depending on the occupational and medical ratings of the proposed insured.\r\nThe contents of this page are for illustrative purposes only. Actual terms and conditions of insurance coverage are found in the policy. In case of conflict, policy terms shall prevail.\r\n ', 2),
(4, 'ACTIVE HEALTH INVEST PLUS', '17000', '18-65 YEARS OLD', 'UNTIL 100 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'Nothing is more important than family. That’s why staying healthy for your loved ones is important. Now, you can have a plan that lets you invest in your health and enjoy rewards for living a healthier lifestyle. That way, you can look forward to a happier, fuller life.', 'REWARDS FOR LIVING HEALTHY\r\nPhilam Vitality is a wellness program that let’s you enjoy rewards while improving your health. Start participating in the different health activities to earn points and get rewarded.\r\n\r\n \r\n\r\n \r\n\r\nLEARN MORE ABOUT PHILAM VITALITY \r\nHEALTHCARE AND LIFE INSURANCE BENEFITS\r\nThis product is a variant of Health Invest Plus. It includes benefits for critical illnesses and early stage conditions, accident coverage, and a growing health fund for future needs. You can also add hospitalization benefits. Lastly, it gives you life insurance for your family’s needs if anything happens to you.\r\n\r\n \r\n\r\n \r\n\r\nLEARN MORE ABOUT CRITICAL ILLNESS COVERAGE \r\nLIFE INSURANCE COVERAGE\r\nEnjoy exclusive insurance privileges as you live healthier.\r\n\r\n \r\n\r\nWith Active Health Invest Plus, you instantly get additional coverage of 20% on your health, accident and life insurance.\r\n\r\n \r\n\r\nYou can even get higher additional coverage as you go along. The higher your Philam Vitality Status each year, the more rewards you get.\r\n\r\n \r\n\r\nLEARN HOW YOU CAN GET MORE ADDITIONAL COVERAGE IN SUCCEEDING YEARS ', 'Active Health Invest Plus is a Health Invest Plus variant with built-in Early Stage Critical Illness (ESCI), Waiver of Premium for Critical Illness (WPCI) and Accident and Health (A&H) riders. The Hospital Confinement Benefit rider is optional. It also includes Philam Vitality.\r\nIf you choose to discontinue your Philam Vitality Membership, you will no longer be availing of the Active variant and only the Health Invest Plus plan will continue.\r\nYou are exempted from future payments through the WPCI rider. The WPCI rider waives the basic premiums, regular top-ups and special top-ups in case of diagnosis of any of the 56 covered major critical illnesses which commenced at least 90 days after the effective date.\r\nOnce a claim has been made for any of the 56 major critical illnesses or when the Early Stage Benefit has been claimed 4 times for different conditions, the ESCI rider will terminate.\r\nThe additional coverage on any policy this year can be as much as 50% and cannot be lower than zero. This will continue as long as your Active Health Invest Plus plan and your Philam Vitality Membership is in force.\r\nPlease consult your Financial Advisor or refer to the policy contract for full details on the critical illnesses and hospital confinement benefit definitions, exclusions and limitations.', 2),
(5, 'Buddyguard ', '13000', '18-64 YEARS OLD', '', 'Philam life financial advisor', '\r\n\r\nInsuring those who put their lives on the line to protect others is a job of a good employer. Give your security personnel peace of mind knowing that someone is watching over them.\r\n\r\nPhilam Life\'s Buddyguard offers life and accident protection for those who safeguard our community.\r\n', '\r\nLIFE INSURANCE BENEFIT\r\n\r\nProvides your employees insurance protection anytime, anywhere and against any cause of loss of life.\r\n\r\nACCIDENTAL DEATH, DISMEMBERMENT & DISABLEMENT BENEFIT\r\n\r\nProvides cash in case your security guard passes away, gets dismembered or disabled due to an accident.\r\n\r\nACCIDENT MEDICAL REIMBURSEMENT BENEFIT\r\n\r\nAllows your security guards to reimburse their medical expenses up to a maximum amount from accident-related causes.', '\r\n\r\n    All benefits are subject to the maximum amounts as specified in the Schedule of Benefits.\r\n    The final interpretation of any specific provision or its applicability is subject to the Master Policy issued by The Philippine American Life & General Insurance Company as accepted by the Assured Company.\r\n\r\n', 16),
(6, 'Kids ', '23000', '3-24 YEARS OLD', '', 'Philam life financial advisor', '\r\n\r\nGive your students\' parents peace of mind while providing them with the best environment for learning. Secure not only every student’s educational growth but overall protection as well.\r\n\r\nPhilam Life\'s affordable insurance plan for Kids provides life and accident protection to help ensure your students\' well-being.\r\n', '\r\nLIFE INSURANCE BENEFIT\r\n\r\nProvides your employees insurance protection anytime, anywhere and against any cause of loss of life.\r\n\r\nACCIDENTAL DEATH, DISMEMBERMENT & DISABLEMENT BENEFIT\r\n\r\nProvides cash in case your student and/or teacher passes away, gets dismembered or disabled due to an accident.\r\n\r\nACCIDENT MEDICAL REIMBURSEMENT BENEFIT\r\n\r\nAllows your students to reimburse their medical expenses up to a maximum amount from accident-related causes.\r\n\r\n', '\r\n\r\n    All benefits are subject to the maximum amounts as specified in the Schedule of Benefits.\r\n    The final interpretation of any specific provision or its applicability is subject to the Master Policy issued by The Philippine American Life & General Insurance Company as accepted by the Assured Company.\r\n\r\n', 16),
(7, 'Classic Corporate Personal Accident', '15000', '18-64 YEARS OLD', '', 'Philam life financial advisor', '\r\n\r\nMake sure your company’s greatest asset - your people – are protected from unforeseen incidents, be it at work, an event or in transit. Ensure their and their families’ financial future.\r\n\r\nPhilam Life\'s Classic Corporate Personal Accident offers comprehensive and customizable accident protection for your business.\r\n', '\r\nACCIDENTAL DEATH BENEFIT\r\n\r\nProvides cash  in case your employee passes away due to an accident.\r\n\r\nACCIDENTAL DISMEMBERMENT AND LOSS OF USE BENEFIT\r\n\r\nProvides a specified amount to your employee in case of dismemberment or loss of use of specific body parts due to an accident.\r\n\r\nACCIDENTAL PERMANENT TOTAL DISABILITY BENEFIT\r\n\r\nPays cash to your employee monthly in case of total and permanent disability due to an accident.\r\n\r\nCOVERAGE FOR MURDER AND HOMICIDE\r\n\r\nPays cash of up to PHP 2 Million in case your employee passes away due to murder or any attempt and homicide or any attempt unprovoked by the employee. \r\n\r\nFLYING COVERAGE\r\n\r\nPays cash in case your employee passes away while traveling as a regular passenger of any commercial flight due to an accident.\r\n\r\nRENEWAL BONUS\r\n\r\nIncrease your employees’ coverage each year for five consecutive years upon renewal.\r\n\r\n', '\r\n\r\n    All benefits are subject to the maximum amounts as specified in the Schedule of Benefits.\r\n    The final interpretation of any specific provision or its applicability is subject to the Master Policy issued by The Philippine American Life & General Insurance Company as accepted by the Assured Company.\r\n\r\n', 15),
(8, 'JR. CORPORATE PERSONAL ACCIDENT', '16000', '18-64 YEARS OLD', '', 'Philam life financial advisor', '\r\n\r\nAccidents can happen anytime and anywhere. Best to protect your people from unforeseen incidents that can cripple them and their family physically, emotionally and even financially.\r\n\r\nJunior Corporate Personal Accident allows you to provide meaningful benefits against accidents for your small business.\r\n', '\r\nACCIDENTAL DEATH BENEFIT\r\n\r\nProvides cash  in case your employee passes away due to an accident.\r\n\r\nACCIDENTAL DISMEMBERMENT AND LOSS OF USE BENEFIT\r\n\r\nProvides a specified amount to your employee in case of dismemberment or loss of use of specific body parts due to an accident.\r\n\r\nACCIDENTAL PERMANENT TOTAL DISABILITY BENEFIT\r\n\r\nPays cash to your employee monthly in case of total and permanent disability due to an accident.\r\n\r\nCOVERAGE FOR MURDER AND HOMICIDE\r\n\r\nPays cash of up to PHP 2 Million in case your employee passes away due to murder or any attempt and homicide or any attempt unprovoked by the employee. \r\n\r\nFLYING COVERAGE\r\n\r\nPays cash in case your employee passes away while traveling as a regular passenger of any commercial flight due to an accident.\r\n\r\nRENEWAL BONUS\r\n\r\nIncrease your employees’ coverage each year for five consecutive years upon renewal.\r\n\r\n', '\r\n\r\n    All benefits are subject to the maximum amounts as specified in the Schedule of Benefits.\r\n    The final interpretation of any specific provision or its applicability is subject to the Master Policy issued by The Philippine American Life & General Insurance Company as accepted by the Assured Company.\r\n\r\n', 15),
(11, 'EDUCATION', '14000', '18-55 YEARS OLD', '0-10 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'As parents, you aspire for a successful future for your children. You want them to have a good education and a better life. You understand that education is one of the greatest gifts that you can give to your children.\r\n\r\nFuture Scholar is an investment and life insurance plan that gives guaranteed education benefits and long-term growth potential. \r\n\r\nFuture Scholar is available at an affordable minimum premium of PHP 20,000 paid annually. ', 'GUARANTEED EDUCATION BENEFITS\r\nYour child gets education benefits in eight semi-annual guaranteed cash payouts for four years of education starting at age 18. \r\n\r\nLONG-TERM GROWTH POTENTIAL\r\nEnjoy long-term growth potential through access to expertly-managed funds. \r\n\r\nPLAN CONTINUATION BENEFITS\r\nHave peace of mind knowing that should you pass away or suffer total and permanent disability, the plan will continue without you having to make future payments. \r\n\r\nCHOICE OF PAYMENT TERMS\r\nYou can build your child\'s education fund in 5 years or until your child reaches the age of 17. ', 'Future Scholar is a packaged unit-linked plan and a Guaranteed Education Benefit rider. It puts your premiums into two allocations: guaranteed savings and investment.\r\nThe investment allocation allows you to grow an Account Value over time. This benefit is not guaranteed and is dependent on the performance of your chosen funds. \r\nPayment Period is not guaranteed. In the future, if the Account Value becomes insufficient to pay for relevant charges and premiums due, it is possible that you may be required to pay additional premiums so that the policy will remain in force. \r\nThe coverage of the plan continuation benefits is premium paying period or payor\'s age 60, whichever is earlier.\r\nThe term life insurance coverage for the payor is up to child\'s age 18 or payor\'s age 60, whichever is earlier. \r\nThe minimum premium excludes the premium for the optional term life insurance coverage.\r\nMinimum premium of PHP 20,000 is applicable to annual payment mode only. The minimum premium for monthly, quarterly, and semi-annual modes is PHP 30,000 a year. \r\nThis product is valid for purchase in the Philippines only. \r\nThe contents of this page are for explanatory purposes only. Actual terms and conditions are found in the policy. In case of conflict, policy terms shall prevail.', 7),
(12, 'MONEYWORKS FOR EDUCATION', '14000', '18-65 YEARS OLD', 'UNTIL 100 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'We all want our children to have a bright future. We work hard so we can equip them with the best education. However, our hard earned savings are not growing as much as we want them to.\r\n\r\nGet a head start in building your child’s education fund no matter what happens to you with Philam Life’s MoneyWorks for Education. It\'s a unit-linked savings plan where benefits are connected to the performance of your chosen investment funds, allowing you to grow your money better over time. Plus, you also get lifetime insurance coverage to protect you if the unexpected happens.\r\n\r\nMoneyWorks for Education is available at a minimum premium of PHP 20,000 a year.', 'HIGH LONG TERM EARNING POTENTIAL\r\nMoneyWorks for Education gives you access to expertly-managed funds that can earn you an account value with higher returns than bank deposits.\r\nFLEXIBLE FUND CHOICES\r\nGet full control of your savings plan based on the risk you are willing to take. You have the privilege to increase your investment anytime. Plus, you can switch funds and change fund allocations whenever you like free-of-charge.\r\n\r\nPLAN CONTINUATION\r\nYour plan includes a waiver of premium for critical illness. Should you encounter any of 56 covered critical illnesses or become Totally and Permanently disabled, your plan will continue without you needing to pay future premiums.\r\n\r\n \r\n\r\nVIEW LIST OF CRITICAL ILLNESSES \r\nLIFE INSURANCE COVERAGE\r\nLeave a cash amount for your loved ones when you pass away so you can make sure that their needs are taken care of. In case your account value becomes higher than your life insurance coverage when you pass away, your loved ones can get the higher amount instead.\r\n\r\nEASY ON THE POCKET OPTIONS\r\nStart saving for as low as PHP 2,500 per month regularly or in as short as 5, 7, or 10 years.', 'This is a unit-linked product. It invests a portion of your premiums in funds that allow you to grow an account value over time. It is a MoneyWorks variant with a packaged Waiver of Premium for Critical illness benefit.\r\nPlease consult your Financial Advisor or refer to the policy contract for full details on the critical illness definitions, exclusions and limitations.\r\nPayment Period is not guaranteed. In the future, if the accumulated Account Value becomes insufficient to pay for charges, it is possible that top-ups (i.e. additional premiums) may be required to continuously enjoy the benefits of this plan.\r\nThe minimum premium required may increase depending on the occupational and medical ratings of the proposed insured.\r\nThe contents of this page are for illustrative purposes only. Actual terms and conditions of insurance coverage are found in the policy. In case of conflict, policy terms shall prevail.', 7),
(13, 'MONEYWORKS', '20000', '0 TO 70 YEARS OLD', 'UNTIL 100 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'You work hard to achieve your dreams. However, your hard earned savings are not growing as much as you want them to.\r\n\r\nGet a head start in achieving your dreams faster with Philam Life’s MoneyWorks. It is a unit-linked savings plan where benefits are connected to the performance of your chosen investment fund and a lifetime insurance coverage.\r\n\r\nMoneyWorks is available at a minimum premium of PHP 20,000 a year.', 'HIGH LONG TERM EARNING POTENTIAL\r\nGrow your money faster through expertly-managed funds that earn you an account value with higher returns than bank deposits over time.\r\n\r\nLIFE INSURANCE COVERAGE\r\nLeave a cash amount for your loved ones when you pass away so you can make sure that their needs are taken care of. In case your account value becomes higher than your life insurance coverage when you pass away, your loved ones can get the higher amount instead.\r\n\r\nFLEXIBLE FUND CHOICES\r\nGet full control of your savings plan based on the risk you are willing to take. You have the privilege to increase your investment anytime. Plus, you can switch funds and change fund allocations whenever you like free-of-charge. \r\n\r\nEASY ON THE POCKET OPTIONS\r\nStart saving for as low as PHP 2,500 per month regularly or in as short as 5, 7, or 10 years.', 'This is a unit-linked product. It invests a portion of your premiums in funds that allow you to grow an account value over time.\r\nPlease consult your Financial Advisor or refer to the policy contract for full details on the critical illness definitions, exclusions and limitations.\r\nPremium period is not guaranteed. In the future, if the account value becomes insufficient to pay for charges, it is possible that additional premiums (i.e. top-ups) will be required to continuously enjoy the benefits of this plan and its riders.\r\nThe amount of your account value will vary depending on fund performance which may be affected by market conditions.\r\nThe minimum premium required may increase depending on the occupational and medical ratings of the proposed insured.\r\nThe contents of this page are for illustrative purposes only. Actual terms and conditions of insurance coverage are found in the policy. In case of conflict, policy terms shall prevail.\r\n ', 8),
(14, 'FUTURE PROTECT', '21000', '0 TO 58 YEARS OLD', 'UNTIL 70 YEARS OLD', 'PHILAM LIFE FINANCIAL ADVISOR', 'Retirement is a time to enjoy all the things you never had time to do. It is the phase of your life when you just want to sit back, relax and live without worries.\r\n\r\nPhilam Life’s Future Protect can take care of your future needs. It’s a plan for you when you grow old, or for your loved ones to remember you by should the unexpected happen.\r\n\r\nFuture Protect is available at a minimum premium of PHP 20,000 a year.', 'HIGH LONG TERM EARNING POTENTIAL\r\nGrow your money faster through expertly-managed funds that can earn you an account value with higher returns than bank deposits over time.\r\n\r\nEXPERT ADMINISTRATION OF YOUR FUNDS\r\nEnjoy stress-free administration of your regular and increasing cash payouts to serve as continuous income for 10 years when you grow old.\r\n\r\n \r\n\r\nCHOOSE WHEN TO RECEIVE YOUR CASH PAYOUTS\r\nStart receiving your regular cash payouts either at ages 55, 60, 65 or 70.\r\n\r\nFLEXIBLE PAYMENT TERMS\r\nStart building your fund in easy payment terms of 7, 10, 15 or 20 years.\r\n\r\nPLAN CONTINUATION\r\nShould you become totally and permanently disabled, the plan will continue without you having to pay succeeding premiums. This allows your fund to remain intact.\r\n\r\nGROWING LIFE INSURANCE COVERAGE\r\nIf you pass away before your chosen retirement age, your family will receive a cash amount in the form of guaranteed and increasing payouts for 10 years. You can choose a special date when your family will receive the benefits to remember you by.', 'This is a unit-linked product. It invests a portion of your premiums in funds that allow you to grow an account value over time.\r\nUpon reaching chosen retirement age, your Account Value will be fully withdrawn and shall be placed under a settlement arrangement with Philam Life. Under the settlement agreement, you shall receive guaranteed and increasing cash payouts for 10 years.\r\nPlease consult your Financial Advisor or refer to the policy contract for full details on the critical illness definitions, exclusions and limitations.\r\nPremium period is not guaranteed. In the future, if the account value becomes insufficient to pay for charges, it is possible that additional premiums (i.e. top-ups) will be required to continuously enjoy the benefits of this plan and its riders.\r\nThe amount of your account value will vary depending on fund performance which may be affected by market conditions.\r\nThe minimum premium required may increase depending on the occupational and medical ratings of the proposed insured.\r\nThe contents of this page are for illustrative purposes only. Actual terms and conditions of insurance coverage are found in the policy. In case of conflict, policy terms shall prevail.', 8);

-- --------------------------------------------------------

--
-- Table structure for table `philam_services_offered`
--

CREATE TABLE `philam_services_offered` (
  `pso_service_offeredid` int(11) NOT NULL,
  `pso_service_name` varchar(250) NOT NULL,
  `pso_icon` varchar(500) NOT NULL,
  `pso_service_desc` varchar(2500) NOT NULL,
  `pso_service_dateadded` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pso_service_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `philam_services_offered`
--

INSERT INTO `philam_services_offered` (`pso_service_offeredid`, `pso_service_name`, `pso_icon`, `pso_service_desc`, `pso_service_dateadded`, `pso_service_type`) VALUES
(1, 'Protection', 'protection.jpg', 'Make sure that the needs of your loved ones are taken care of no matter what happens to you.', '2019-11-04 13:59:34', 1),
(2, 'Health', 'health.png', 'Get coverage for expensive health setbacks so you can focus on getting better.', '2019-11-04 14:00:14', 1),
(7, 'Education', 'education.png', 'When the time comes, be ready to give your child the best opportunities available.\r\n', '2019-11-04 14:02:28', 1),
(8, 'Savings', 'savings.png', 'Have the right savings plan to help you achieve your dream goal.\r\n', '2019-11-04 14:02:28', 1),
(9, 'Investments', 'investment.png', 'Grow your wealth further and get the most out of life.', '2019-11-04 14:02:28', 1),
(14, 'Group Life', 'group_life.jpg', 'Increase engagement by providing your employees more than the basic paycheck.\r\n', '2019-11-04 14:04:33', 2),
(15, 'Group Accident', 'group_accident.png', 'Have peace of mind when you protect your people from unforeseen incidents.\r\n', '2019-11-04 14:04:33', 2),
(16, 'Group Medical', 'group_medical.png', 'Grow your company by ensuring your employees\' health.\r\n', '2019-11-04 14:04:33', 2);

-- --------------------------------------------------------

--
-- Table structure for table `philam_services_type`
--

CREATE TABLE `philam_services_type` (
  `pst_service_typeid` int(11) NOT NULL,
  `pst_service_servicename` varchar(250) NOT NULL,
  `pst_service_type_dateadded` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `philam_services_type`
--

INSERT INTO `philam_services_type` (`pst_service_typeid`, `pst_service_servicename`, `pst_service_type_dateadded`) VALUES
(1, 'Personal', '2019-11-04 13:55:36'),
(2, 'Business', '2019-11-04 14:03:07');

-- --------------------------------------------------------

--
-- Table structure for table `philam_wallets`
--

CREATE TABLE `philam_wallets` (
  `pw_waleltid` int(11) NOT NULL,
  `pw_userid` int(11) NOT NULL,
  `pw_processedmoney` decimal(10,0) NOT NULL,
  `pw_processfee` decimal(10,0) NOT NULL,
  `pw_processtype` int(11) NOT NULL,
  `pw_processdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `philam_wallets`
--

INSERT INTO `philam_wallets` (`pw_waleltid`, `pw_userid`, `pw_processedmoney`, `pw_processfee`, `pw_processtype`, `pw_processdate`) VALUES
(17, 9, '1000', '10', 0, '2019-11-10 16:26:24'),
(18, 9, '1000', '10', 0, '2019-11-10 16:26:25'),
(19, 9, '384', '10', 1, '2019-11-10 16:26:45'),
(20, 9, '384', '10', 1, '2019-11-10 16:26:45'),
(21, 9, '384', '10', 1, '2019-11-10 16:29:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `philam_client_credentials`
--
ALTER TABLE `philam_client_credentials`
  ADD PRIMARY KEY (`pcc_accountid`);

--
-- Indexes for table `philam_products_offered`
--
ALTER TABLE `philam_products_offered`
  ADD PRIMARY KEY (`ppo_productid`),
  ADD KEY `ppo_serviceid` (`ppo_serviceid`);

--
-- Indexes for table `philam_services_offered`
--
ALTER TABLE `philam_services_offered`
  ADD PRIMARY KEY (`pso_service_offeredid`),
  ADD KEY `pso_service_type` (`pso_service_type`);

--
-- Indexes for table `philam_services_type`
--
ALTER TABLE `philam_services_type`
  ADD PRIMARY KEY (`pst_service_typeid`);

--
-- Indexes for table `philam_wallets`
--
ALTER TABLE `philam_wallets`
  ADD PRIMARY KEY (`pw_waleltid`),
  ADD KEY `pw_userid` (`pw_userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `philam_client_credentials`
--
ALTER TABLE `philam_client_credentials`
  MODIFY `pcc_accountid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `philam_products_offered`
--
ALTER TABLE `philam_products_offered`
  MODIFY `ppo_productid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `philam_services_offered`
--
ALTER TABLE `philam_services_offered`
  MODIFY `pso_service_offeredid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `philam_services_type`
--
ALTER TABLE `philam_services_type`
  MODIFY `pst_service_typeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `philam_wallets`
--
ALTER TABLE `philam_wallets`
  MODIFY `pw_waleltid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `philam_products_offered`
--
ALTER TABLE `philam_products_offered`
  ADD CONSTRAINT `philam_products_offered_ibfk_1` FOREIGN KEY (`ppo_serviceid`) REFERENCES `philam_services_offered` (`pso_service_offeredid`);

--
-- Constraints for table `philam_services_offered`
--
ALTER TABLE `philam_services_offered`
  ADD CONSTRAINT `philam_services_offered_ibfk_1` FOREIGN KEY (`pso_service_type`) REFERENCES `philam_services_type` (`pst_service_typeid`);

--
-- Constraints for table `philam_wallets`
--
ALTER TABLE `philam_wallets`
  ADD CONSTRAINT `philam_wallets_ibfk_1` FOREIGN KEY (`pw_userid`) REFERENCES `philam_client_credentials` (`pcc_accountid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
