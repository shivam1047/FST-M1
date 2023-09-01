import win32clipboard
from selenium import webdriver
from selenium.webdriver import ActionChains, Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())

with webdriver.Firefox(service=service) as driver:

    driver.get("https://training-support.net/selenium/dynamic-controls")

    print("Title of Dynamic Controls page: ", driver.title)

    wait = WebDriverWait(driver, 10)

    textbox = driver.find_element(By.ID, "input-text")

    textbox_toggle = driver.find_element(By.ID, "toggleInput")

    print("Text box is enabled: ", textbox.is_enabled())

    textbox_toggle.click()

    print("Text box is enabled: ", textbox.is_enabled())

    textbox.send_keys("Selenium with Python")

    driver.close()

