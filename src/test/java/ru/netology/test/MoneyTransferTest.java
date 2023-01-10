package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFromFirstCard() {
        var loginPage = open("http://localhost:9999", LoginPage.class);

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var cardInfoFirst = DataHelper.getFirstCardInfo();
        var cardInfoSecond = DataHelper.getSecondCardInfo();
        var amount = DataHelper.amountTransfer(100);
        var firstCardBalance = dashboardPage.getCardBalance(cardInfoFirst);
        var secondCardBalance = dashboardPage.getCardBalance(cardInfoSecond);
        var expectedFirstCardBalance = firstCardBalance + amount;
        var expectedSecondCardBalance = secondCardBalance - amount;
        var transferPage = dashboardPage.selectCard(cardInfoFirst);
        transferPage.validTransfer(String.valueOf(amount), cardInfoSecond);

        assertEquals(expectedFirstCardBalance, dashboardPage.getCardBalance(cardInfoFirst));
        assertEquals(expectedSecondCardBalance, dashboardPage.getCardBalance(cardInfoSecond));
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {
        var loginPage = open("http://localhost:9999", LoginPage.class);

        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var cardInfoFirst = DataHelper.getFirstCardInfo();
        var cardInfoSecond = DataHelper.getSecondCardInfo();
        var amount = DataHelper.amountTransfer(100);
        var firstCardBalance = dashboardPage.getCardBalance(cardInfoFirst);
        var secondCardBalance = dashboardPage.getCardBalance(cardInfoSecond);
        var expectedFirstCardBalance = firstCardBalance - amount;
        var expectedSecondCardBalance = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCard(cardInfoSecond);
        transferPage.validTransfer(String.valueOf(amount), cardInfoFirst);

        assertEquals(expectedFirstCardBalance, firstCardBalance);
        assertEquals(expectedSecondCardBalance, dashboardPage.getCardBalance(cardInfoSecond));
    }
}

