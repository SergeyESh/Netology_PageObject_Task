package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        amount.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountTransfer);
        from.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }


}
