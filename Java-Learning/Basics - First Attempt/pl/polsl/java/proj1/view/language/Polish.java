/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.proj1.view.language;

/**
 * Concrete class of the bridge implementer, stands for Polish language (PL).
 *
 * @author MarczykLAP
 * @version 1.0.0
 */
public class Polish implements LanguageImplementer {

    /**
     * Non-parametric constructor.
     */
    public Polish() {

    }

    @Override
    public void showMenu() {
        System.out.println("[1] - Wykonaj test fermata");
        System.out.println("[2] - Wyjdz");
    }

    @Override
    public void msgLanguageSetTo(String language) {
        System.out.println("Jezyk interfejsu jest ustawiony na: " + language + ".");
    }

    @Override
    public void msgLanguageInvalid() {
        System.out.println("Wybrany jezyk jest nieprawidlowy, jezyk zostaje ustawiony na domyslny.");
    }

    @Override
    public void msgInitializationFinished() {
        System.out.println("Inicjalizacja zakonczona.");
    }

    @Override
    public void msgExit() {
        System.out.println("Wybrano wyjscie z programu.");
    }

    @Override
    public void msgExceptionArrayIndexOutOfBounds() {
        System.out.println("Nieprawidlowe dane uruchomieniowe.");
    }

    @Override
    public void msgExceptionIO() {
        System.out.println("Blad podczas odczytu danych.");
    }

    @Override
    public void msgExceptionNumberFormat() {
        System.out.println("Dane wejsciowe sa nieprawidlowe.");
    }

    @Override
    public void msgExceptionNotNumericUnsignedValue() {
        System.out.println("Dane wejsciowe zawieraja znaki niebedace cyframi.");
    }

    @Override
    public void msgExceptionWrongNumericalInterval() {
        System.out.println("Liczba nie spelnia wymagan wobec przedzialu.");
    }

    @Override
    public void msgWrongOption() {
        System.out.println("Wybrales nieprawidlowa opcje.");
    }

    @Override
    public void askForFermatNumber() {
        System.out.println("Prosze, wpisz liczbe ktora chcesz sprawdzic.");
        System.out.println("Liczba musi byc wieksza od 1!.");
    }

    @Override
    public void askForFermatNumberForTest() {
        System.out.println("Wpisz wartosc z przedzialu <1, testowana liczba) dla ktorej bedzie "
                + "wykonywany test Fermata.");
    }

    @Override
    public void showFermatTestOptions() {
        System.out.println("Test wymaga wartosci odniesienia dla ktorej bedzie wykonywany.");
        System.out.println("1 - Chce sam podac wartosc dla ktorej ma byc przeprowadzony test");
        System.out.println("2 - Chce aby program sam wygenerowal losowa wartosc");
        System.out.println("3 - Anuluj - powrot do menu");
    }

    @Override
    public void showGeneratedNumberToTest() {
        System.out.println("Wygenerowana wartosc odniesienia dla testu to: ");
    }

    @Override
    public void showTestResults(String testedNumber, String testedOnValue, boolean resultOfTest) {
        System.out.println("Test zostal przeprowadzony dla liczby: " + testedNumber + ".");
        System.out.println("Wykonany zostal na podstawie liczby: " + testedOnValue + ".");
        String tmp;
        if (resultOfTest) {
            tmp = "Pozytywny";
        } else {
            tmp = "Negatywny";
        }
        System.out.println("Wynik testu: " + tmp + ".");

    }

}
