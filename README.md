# QualityMindsTest
Project basic on website of Quality Minds company - recruitment task

Task 1 and Task 2 run parallel on chrome and firefox.

Task 3 does not run parallel because in step 11 is required usage of upload button.
Because of that I have to use java robot (other option is autoit).
There is other way to upload file - sending path into input element on website, 
then the system window does not appear.

How to run report: allure serve path_to_target/allure-results


Fun fact: in firefox there is no .pdf extension in flyer file