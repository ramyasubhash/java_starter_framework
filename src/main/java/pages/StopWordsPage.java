package pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StopWordsPage extends BasePage {

	public StopWordsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * This section covers web elements definition
	 */
	@FindBy(xpath = "//h1[contains(text(), 'Stop Words Library')]")
	public WebElement stopWordsLibraryTitle;

	@FindBy(xpath = "//h1[contains(text(), 'Stop Words')]")
	public WebElement stopWordsTitle;

	@FindBy(xpath = "//h4[contains(text(), 'Stop Words')]")
	public WebElement stopWordsLayoutTitle;

	@FindBy(xpath = "//h4[contains(text(), 'Stop Words Definition')]")
	public WebElement stopWordsDefinition;

	@FindBy(xpath = "//button[contains(text(), ' Add Stop Word ')]")
	public WebElement addStopWordButton;

	@FindBy(id = "stopWordName")
	public WebElement stopWordName;

	@FindBy(xpath = "//input[@role='combobox']")
	public WebElement stopWordTextBox;

	@FindBy(xpath = "//div[@class='col-md-9 multiple-select no-dropdown']")
	public WebElement comboBoxValues;

	@FindBy(xpath = "//button[contains(text(), 'Options')]")
	public WebElement optionsButton;

	@FindBy(xpath = "//div[@class='dropdown-content dropdown-menu show']//button[1]")
	public WebElement saveOption;

	@FindBy(xpath = "//div[@class='dropdown-content dropdown-menu show']//button[2]")
	public WebElement saveExitOption;

	@FindBy(xpath = "//tbody[@class='ui-table-tbody']")
	public WebElement stopWordsTable;

	@FindBys(@FindBy(xpath = "//tbody[@class='ui-table-tbody']//tr"))
	public List<WebElement> stopWordsTableRows;

	@FindBys(@FindBy(xpath = "//tbody[@class='ui-table-tbody']//tr[3]//td[2]"))
	public WebElement stopWordsTableColumn;

	@FindBys(@FindBy(xpath = "//tbody[@class='ui-table-tbody']//tr[3]//td[1]"))
	public WebElement stopWordsLastColumn;

	@FindBys(@FindBy(xpath = "//div[@role='checkbox']"))
	public List<WebElement> checkBoxes;

	@FindBys(@FindBy(xpath = "//button[@class='btn-link']"))
	public List<WebElement> buttonLink;

	@FindBy(xpath = "//span[contains(text(), 'View/Edit')]")
	public WebElement viewEditButton;

	@FindBy(xpath = "//button[contains(text(), 'Delete ')]")
	public WebElement deleteButton;

	@FindBys(@FindBy(xpath = "//div[@class='ui-chkbox ui-widget']"))
	public List<WebElement> tableCheckboxes;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(), 'Yes')]")
	public WebElement dialogButtonYes;

	public void WaitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void selectStopWordsForUpdate(int nthelement) throws InterruptedException {
		WaitForElement(stopWordsLayoutTitle);

		List<WebElement> elements = buttonLink;
		if (elements.get(nthelement - 1).isEnabled()) {
			elements.get(nthelement - 1).click();
		}

	}

	public void selectStopWordForDeletion(int nthelement) throws InterruptedException {
		WaitForElement(stopWordsLayoutTitle);

		List<WebElement> elements = checkBoxes;
		if (elements.get(nthelement - 1).isEnabled()) {
			elements.get(nthelement - 1).click();
		}

	}

	public void selectStopWordsForDeletion() throws InterruptedException {
		WaitForElement(stopWordsLayoutTitle);
		List<WebElement> elements = checkBoxes;
		Thread.sleep(3000);

		for (int i = elements.size() - 1; i > elements.size() - 3; i--) {

			elements.get(i).click();
		}

	}

	public boolean validateStopWordPresent(String stopword) {
		List<WebElement> elementrows = stopWordsTableRows;

		for (int i = elementrows.size() - 1; i > 0; i--) {

			String stopwordpresent = elementrows.get(i).getText();
			if (stopwordpresent.equalsIgnoreCase(stopword)) {
				return true;

			}

		}
		return false;
	}

	public boolean validateStopWordPresent(int nthelement) {
		WaitForElement(stopWordsLayoutTitle);
		List<WebElement> elementrows = stopWordsTableRows;

		String expected = elementrows.get(nthelement - 1).getText();

		for (int i = elementrows.size() - 1; i > 0; i--) {

			String stopwordpresent = elementrows.get(i).getText();

			if (stopwordpresent.equalsIgnoreCase(expected)) {
				return false;

			}

		}
		return true;
	}

	/**
	 * adding/creating stopword
	 */
	public boolean addingStopWord(String stopwordname, String stopwardsInput)
			throws InterruptedException, AWTException {
		Thread.sleep(3000);
		WaitForElement(addStopWordButton);
		addStopWordButton.click();
		WaitForElement(stopWordName);

		stopWordName.sendKeys(stopwordname);
		String[] stopwardsInputList = stopwardsInput.split(":");
		for (int i = 0; i < stopwardsInputList.length; i++) {
			stopWordTextBox.sendKeys(stopwardsInputList[i]);
			Thread.sleep(3000);
			clickEnter();

		}
		Thread.sleep(3000);
		clickTab(stopWordTextBox);
		Thread.sleep(3000);
		optionsButton.click();
		saveExitOption.click();
		WaitForElement(stopWordsTitle);
		Thread.sleep(3000);
		return validateStopWordPresent(stopwordname);

	}

	/**
	 * editing/updating stopword
	 */
	public String updateStopWord(int nthelementToUpdate, String updateStopwords)
			throws InterruptedException, AWTException {
		Thread.sleep(3000);

		selectStopWordsForUpdate(nthelementToUpdate);
		Thread.sleep(3000);
		viewEditButton.click();
		WaitForElement(stopWordsDefinition);
		WaitForElement(stopWordName);
		Thread.sleep(3000);
		stopWordTextBox.sendKeys(updateStopwords);
		Thread.sleep(3000);
		clickEnter(stopWordTextBox);
		Thread.sleep(3000);
		clickEnter();
		Thread.sleep(3000);
		clickTab(stopWordTextBox);
		WaitForElement(optionsButton);
		optionsButton.click();
		saveExitOption.click();
		return updateStopwords;
	}

	/**
	 * validate stopword update
	 */
	public void validateStopWordUpdate(int nthelementToUpdate, String updateStopwords) throws InterruptedException {
		Thread.sleep(3000);
		WaitForElement(stopWordsTitle);
		selectStopWordsForUpdate(nthelementToUpdate);
		viewEditButton.click();
		Thread.sleep(5000);
		optionsButton.click();
		saveExitOption.click();
	}

	/**
	 * delete single stopword
	 */
	public void deleteStopWord(int nthelement) throws InterruptedException {

		Thread.sleep(3000);
		selectStopWordForDeletion(nthelement);
		WaitForElement(deleteButton);
		deleteButton.click();
		dialogButtonYes.click();
	}

	/**
	 * delete multiple stopword
	 */
	public void deleteStopWords() throws InterruptedException {
		Thread.sleep(3000);
		selectStopWordsForDeletion();
		WaitForElement(deleteButton);
		deleteButton.click();
		dialogButtonYes.click();
	}

}
