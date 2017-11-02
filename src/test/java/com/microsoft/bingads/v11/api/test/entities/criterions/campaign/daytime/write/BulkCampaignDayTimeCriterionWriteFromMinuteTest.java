package com.microsoft.bingads.v11.api.test.entities.criterions.campaign.daytime.write;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v11.api.test.entities.criterions.campaign.daytime.BulkCampaignDayTimeCriterionTest;
import com.microsoft.bingads.v11.bulk.entities.BulkCampaignDayTimeCriterion;
import com.microsoft.bingads.v11.campaignmanagement.DayTimeCriterion;
import com.microsoft.bingads.v11.campaignmanagement.Minute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BulkCampaignDayTimeCriterionWriteFromMinuteTest extends BulkCampaignDayTimeCriterionTest {

    @Parameterized.Parameter(value = 1)
    public Minute propertyValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                	{"0", Minute.ZERO},
                    {"15", Minute.FIFTEEN},
                    {"30", Minute.THIRTY},
                    {"45", Minute.FORTY_FIVE}
                }
        );
    }

    @Test
    public void testWrite() {
        testWriteProperty(
                "From Minute",
                datum,
                propertyValue,
                new BiConsumer<BulkCampaignDayTimeCriterion, Minute>() {
                    @Override
                    public void accept(BulkCampaignDayTimeCriterion c, Minute v) {
                        ((DayTimeCriterion)c.getBiddableCampaignCriterion().getCriterion()).setFromMinute(v);
                    }
                }
        );
    }
}
