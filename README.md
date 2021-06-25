# N11-Test-HW Automation - Setup Guide
Setting Up SeleniumGrid
-----------------------
Firt step-><p>Download the Selenium Server from <a href="https://www.selenium.dev/downloads/">here</a>. </p>

Second step-> You can place the Selenium Server .jar file anywhere in your HardDrive. The following steps will launch the hub and the node.

Third step-> On the command prompt, type \
`java -jar selenium-server-standalone-2.30.0.jar -role hub`

Fourt step-> Go your node machine and type \
`java -Dwebdriver.gecko.driver="C:\geckodriver.exe" -jar selenium-server-standalone-3.4.0.jar -role webdriver -hub http://192.168.1.3:4444/grid/register -port 5566`
this command will show driver location and makes node ready.

Fifth step->Go to the Selenium Grid web interface and refresh the page. At this point, you have already configured a simple grid. You are now ready to run a test remotely on Node Machine.



TESTCASE
===========
*Automation have two test cases,

Tum magaza bilgilerinin CSV dosyasına yazilmasi
----------------
* N11.com sayfası açılır ve ve Pop-up'ta tamam butonu tıklanır
* Mağazalar/Mağazaları Gör butonuna tıklanır
* Tüm mağazalar sayfası tıklanır
* Tüm mağazalar sayfası altında yer alan mağazalar (A-Z) csv dosyasına yazılır

Random Magaza Secimi ve yorum konrtolü
--------------------------------------
* N11.com sayfası açılır ve ve Pop-up'ta tamam butonu tıklanır
* Mağazalar/Mağazaları Gör butonuna tıklanır
* Random bir magaza secilir
* Magaza Yorumları açılır
* Magaza Yorum kontrolu yapilir

TESTCASE2
============

Rasgele ürün seçilip sepet kontrolü yapma
------------------------------------------
* N11.com sayfası açılır ve bilgisayar kelimesi aratılır
* Marka listesinden üçüncü marka seçildi
* Listelenen ürünlenden random bir ürün seçildi
* Ürün miktarı textboxu temizlenip 2 arttırıldı
* Sepete Ekle butonuna tıklandı
* Sepetim butonuyla sepetim safyasına geç
* Sepetteki ürün sayısının 2 olduğunu kontrol et

<br />

##  Pre Requisites 

1. Java

2. Maven

3. Selenium

4. Gauge 

<br />
## How to Install Gauge Core

**On Windows**
1. Install Chocolatey by executing the following command. \
` @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString(‘https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"`

2. Install Gauge by executing the following command. \
`choco install gauge`

**On MacOS**
1. Update Homebrew. \
`brew update`

2. Install Gauge using Homebrew. \
`brew install gauge`

**On Linux**
1. First, add Gauge’s GPG key with this command. \
`sudo apt-key adv --keyserver hkp://pool.sks-keyservers.net --recv-keys 023EDB0B`

2. Then add Gauge to the repository list using this command. \
`echo deb https://dl.bintray.com/gauge/gauge-deb nightly main | sudo tee -a /etc/apt/sources.list`

3. Finally, install Gauge using these commands. \
`sudo apt-get update` \
`sudo apt-get install gauge`
<br />

## How to Install Gauge Plugins
1. Open Command Prompt and execute following commands. \
`gauge install java` \
`gauge install html-report` \
`gauge install json-report` \
`gauge install xml-report` \
`gauge install spectacle` \
`gauge install flash`

2. You can check the installation using the following command. \
`gauge -v`

	If the installation is success, it will output like this:

```markdown
    Gauge version: <version number>
    Plugins
    -------
    flash (<version number>)
    html-report (<version number>)
    java (<version number>)
    json-report (<version number>)
    spectacle (<version number>)
    xml-report (<version number>)
```
<br />

