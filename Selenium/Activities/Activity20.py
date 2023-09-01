import win32clipboard
from selenium import webdriver
from selenium.webdriver import ActionChains, Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())

with webdriver.Firefox(service=service) as driver:
    wait = WebDriverWait(driver, 10)
    driver.get("https://training-support.net/selenium/javascript-alerts")

    print("Title of Alerts page: ", driver.title)

    driver.find_element(By.ID, "prompt").click()
    prompt_alert = driver.switch_to.alert
    print(prompt_alert.text)
    prompt_alert.send_keys("Awesome!")
    prompt_alert.accept()

    driver.close()

