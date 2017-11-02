package com.microsoft.bingads.v11.api.test.entities.criterions.campaign.age.write;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v11.api.test.entities.criterions.campaign.age.BulkCampaignAgeCriterionTest;
import com.microsoft.bingads.v11.bulk.entities.BulkCampaignAgeCriterion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BulkCampaignAgeCriterionWriteIdTest extends BulkCampaignAgeCriterionTest {

    @Parameter(value = 1)
    public Long propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"123", 123L},
                        {"9223372036854775807", 9223372036854775807L},
                        {null, null}
                }
        );
    }

    @Test
    public void testWrite() {
        testWriteProperty(
                "Id",
                datum,
                propertyValue,
                new BiConsumer<BulkCampaignAgeCriterion, Long>() {
                    @Override
                    public void accept(BulkCampaignAgeCriterion c, Long v) {
                        c.getBiddableCampaignCriterion().setId(v);
                    }
                }
        );
    }
}
