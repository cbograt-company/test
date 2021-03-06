package com.microsoft.bingads.v11.api.test.entities.ad_extension.app.write;

import com.microsoft.bingads.v11.api.test.entities.ad_extension.app.BulkAppAdExtensionTest;
import com.microsoft.bingads.v11.bulk.entities.BulkAppAdExtension;
import com.microsoft.bingads.v11.campaignmanagement.Date;
import com.microsoft.bingads.v11.campaignmanagement.Schedule;
import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

public class BulkAppAdExtensionWriteFromRowValuesEndDateTest extends BulkAppAdExtensionTest {

    @Parameter(value = 1)
    public Date propertyValue;
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"delete_value", null}
        });
    }

    @Test
    public void testWrite() {
    	this.testWriteProperty("End Date", this.datum, this.propertyValue, new BiConsumer<BulkAppAdExtension, Date>() {
            @Override
            public void accept(BulkAppAdExtension c, Date v) {
            	c.getAppAdExtension().setScheduling(new Schedule());
                c.getAppAdExtension().getScheduling().setEndDate(new Date());
            }
        });
    	
    	datum = "2/13/2016";
    	propertyValue = new Date();
    	propertyValue.setMonth(2);
    	propertyValue.setDay(13);
    	propertyValue.setYear(2016);
    	   	
        this.testWriteProperty("End Date", this.datum, this.propertyValue, new BiConsumer<BulkAppAdExtension, Date>() {
            @Override
            public void accept(BulkAppAdExtension c, Date v) {
            	c.getAppAdExtension().setScheduling(new Schedule());
                c.getAppAdExtension().getScheduling().setEndDate(v);
            }
        });
        
        datum = "delete_value";
    	propertyValue = null;
    	   	
        this.testWriteProperty("End Date", this.datum, this.propertyValue, new BiConsumer<BulkAppAdExtension, Date>() {
            @Override
            public void accept(BulkAppAdExtension c, Date v) {
            	c.getAppAdExtension().setScheduling(new Schedule());
                c.getAppAdExtension().getScheduling().setEndDate(v);
            }
        });
        
        datum = null;
    	propertyValue = null;
    	   	
        this.testWriteProperty("End Date", this.datum, this.propertyValue, new BiConsumer<BulkAppAdExtension, Date>() {
            @Override
            public void accept(BulkAppAdExtension c, Date v) {
            	c.getAppAdExtension().setScheduling(null);
            }
        });
    }
}
