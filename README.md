WBA-Automated-test-Scripts
==========================
### Getting Started

1. Clone this project onto your local machine
2. Create the following file at location "C:\\Selenium\\ID.txt" with text "3000". This will be used to uniquely identify     orders
3. Ensure maven is installed and has been added to the PATH variable
4. Open a command prompt window and change directory to the project folder: "cd [project folder path]"
5. Run the tests using one of the following commands:
          Command                               Scope
          mvn clean verify                      Solo group only (used for development to isolate a small number of tests)
          mvn clean -P QuickTest verify         QuickTest group only (covers basic functionality across CCE and eComm)
          mvn clean -P CCE verify               CCE group only
          mvn clean -P CCE_Orders verify        CCE_Orders group only
          mvn clean -P eComm verify             eComm group only
          mvn clean -P eComm_Orders verify      eComm_Orders group only
          mvn clean -P Upload_Order verify      Upload_Order group only (all tests which involve uploading orders)
          mvn clean -P General verify           General group only (login and forgot password features)

Maven will download all dependencies, including the driver .exe files (chromedriver, firefoxdriver, iedriver etc.), and run the tests. Omit the "clean" phrase if you wish to prevent the deletion of the target folder.

### What should I know?

See the Automated Scripts document or code comments for more details.

### More parameters

Yes you can specify which browser to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie
- -Dbrowser=opera
- -Dbrowser=htmlunit
- -Dbrowser=phantomjs

You don't need to worry about downloading the IEDriverServer, or chromedriver binaries, this project will do that for you automatically.

Not got PhantomJS?  Don't worry that will be automatically downloaded for you as well!

You can specify a grid to connect to where you can choose your browser, browser version and platform:

- -Dremote=true
- -DseleniumGridURL=http://{username}:{accessKey}@ondemand.saucelabs.com:80/wd/hub
- -Dplatform=xp
- -Dbrowser=firefox
- -DbrowserVersion=33

You can even specify multiple threads (you can do it on a grid as well!):

- -Dthreads=2

You can also specify a proxy to use

- -DproxyEnabled=true
- -DproxyHost=localhost
- -DproxyPort=8080

If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots

If you need to force a binary overwrite you can do:

- -Doverwrite.binaries=true

### Troubleshoot

- Command "mvn" is not recognised by cmd :: Ensure Maven has been added to the PATH variable for the entire system
- Maven builds but no tests are run :: Ensure at least one test is present in the selected group
- Tests run but fail all the time :: Try running the test with chrome (default). The tests are liable to fail in other browsers
- Lots of tests fail at "clearCookies" method :: An unexpected alert may be present in one of the tests, causing a stream of failures
- Driver binaries are outdated :: Run "mvn clean verify -Doverwrite.binaries=true" and delete the selenium_standalone_binaries folder in your resources directory
