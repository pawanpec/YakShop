package com.xebiatest;

import com.xebiatest.io.YakOutput;
import com.xebiatest.model.LabYak;
import com.xebiatest.service.YakService;
import com.xebiatest.service.YakServiceImpl;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests App.
 */
public class AppTest
{
    LabYak testYak1 = new LabYak(0,"Betty1",4, LabYak.Sex.FEMALE);
    LabYak testYak2 = new LabYak(1,"Betty2",8, LabYak.Sex.FEMALE);
    LabYak testYak3 = new LabYak(2,"Betty3",9.5, LabYak.Sex.FEMALE);
    YakService yakService = null;
    public AppTest(){
        yakService = new YakServiceImpl();
        ArrayList<LabYak> testYakList = new ArrayList<LabYak>();
        testYakList.add(testYak1);
        testYakList.add(testYak2);
        testYakList.add(testYak3);

        for(LabYak yak : testYakList){
            yakService.calculateAndSaveYieldForDay(yak, 13);
        }

    }



    @Test
    public void yakYieldTest()
    {
        assertEquals("The total milk quantity obtained from LabYak " + testYak1.display(15), 566.85, yakService.getYakYieldForAYak(testYak1,15).getMilk(), 0.01);
        assertEquals("The total skins obtained from LabYak " + testYak1.display(15), 2, yakService.getYakYieldForAYak(testYak1,15).getSkin(), 0.01);

    }

    int timeElapsed = 13;
    @Test
    public void milkQuantityTest()
    {
      assertEquals("The total milk quantity obtained from LabYak "+testYak1.display(13),491.66,testYak1.getTotalMilkQuantity(timeElapsed),0.01);

    }
    @Test
    public void skinsCountTest()
    {
        assertEquals("The skins obtained from LabYak "+testYak1.display(13),1,testYak1.getSkinCount(13));

    }

    @Test
    public void testTotalMilkQuantity()
    {


        assertEquals("The total milk obtained from 3 labyaks  ", 1104.48, yakService.getTotalYakYield(13).getMilk(),0.01);
    }

    @Test
    public void testTotalSkinCount()
    {

        assertEquals("The total skins obtained from 3 labyaks  ", 3, yakService.getTotalYakYield(13).getSkin());

    }

}
