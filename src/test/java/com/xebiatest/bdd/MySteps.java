package com.xebiatest.bdd;

import com.xebiatest.exception.YakException;
import com.xebiatest.service.YakService;
import com.xebiatest.service.YakServiceImpl;
import com.xebiatest.store.YakYield;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import static org.junit.Assert.*;

import com.xebiatest.model.LabYak;
public class MySteps{
    private LabYak labYak = null;
    private int currentAgeInDays = 0;
    private int lastShaveAgeInDays = 0;
    YakService yakService = new YakServiceImpl();
    private int numberOfDaysElapsed = 13;
    @When("the age of LabYak is $currentAge days")
    public void whenTheAgeOfLabYakIsdDays(int currentAge){

        this.currentAgeInDays = currentAge;
    }
    @Then("amount of milk produced should be correct")
    public void thenAmountOfMilkProducedShouldBeCorrect(){
        try {
            assertEquals("The amount of milk obtained from labYak " //labYak.getMilkQuantityForDay(currentAgeInDays)
                    + "at the age " + currentAgeInDays, (50 - currentAgeInDays * 0.03), labYak.getMilkQuantityForDay(currentAgeInDays),0.02);
        }
        catch (YakException e) {
            e.printStackTrace();
        }
    }
    @Given("A LabYak")
    public void givenALabYak(){
        labYak = new LabYak(0,"Betty1",4, LabYak.Sex.FEMALE);

    }
    @When("its age when last shaved was $d days")
    public void whenItsAgeWhenLastShavedWasdDays(int lastShaveAgeInDays){
        this.lastShaveAgeInDays = lastShaveAgeInDays;
    }
    @Then("it is again eligible for shaving today")
    public void thenItIsAgainEligibleForShavingToday(){
        double allowedGapInShave = (8+lastShaveAgeInDays*0.01);
        assertTrue("The Yak is eligible for shaving agina !", currentAgeInDays - lastShaveAgeInDays > allowedGapInShave);
    }

    @Then("check total yield of milk and skins")
    public void thenCheckTotalYieldOfMilkAndSkins(){
        YakYield yield = yakService.getTotalYakYield(numberOfDaysElapsed);
        assertEquals("The total amount of milk liters",1357.98,yield.getMilk(),0.01);//1357.98
        assertEquals("The total amount of milk liters",3,yield.getSkin());
    }
    @Given("3 Lab yaks")
    public void given3LabYaks(){
        LabYak labYak1 = new LabYak(3,"Betty1",4, LabYak.Sex.FEMALE);
        LabYak labYak2 = new LabYak(4,"Betty2",5, LabYak.Sex.FEMALE);
        LabYak labYak3 = new LabYak(5,"Betty3",6, LabYak.Sex.FEMALE);
        yakService.calculateAndSaveYieldForDay(labYak1, numberOfDaysElapsed);
        yakService.calculateAndSaveYieldForDay(labYak2, numberOfDaysElapsed);
        yakService.calculateAndSaveYieldForDay(labYak3, numberOfDaysElapsed);
    }//calculateAndSaveYieldForDay
    @Given("number of days elapsed are 13")
    public void givenNumberOfDaysElapsedAre13(){
        numberOfDaysElapsed = 13;
    }


}