package com.microsoft.bingads.v11.api.test.entities.criterions.campaign.age.read;

import com.microsoft.bingads.v11.api.test.entities.criterions.campaign.age.BulkCampaignAgeCriterionTest;
import com.microsoft.bingads.v11.bulk.entities.BulkCampaignAgeCriterion;
import com.microsoft.bingads.v11.campaignmanagement.BidMultiplier;
import com.microsoft.bingads.v11.campaignmanagement.BiddableCampaignCriterion;
import com.microsoft.bingads.internal.functionalinterfaces.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BulkCampaignAgeCriterionReadBidAdjustmentTest extends BulkCampaignAgeCriterionTest {

    @Parameter(value = 1)
    public Double expectedResult;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"0.58", 0.58},
                        {"0.0", 0.0},
                        {"", null},
                        {null, null}
                }
        );
    }

    @Test
    public void testRead() {
        testReadProperty(
                "Bid Adjustment",
                datum,
                expectedResult,
                new Function<BulkCampaignAgeCriterion, Double>() {
                    @Override
                    public Double apply(BulkCampaignAgeCriterion c) {
                        return ((BidMultiplier) ((BiddableCampaignCriterion) c.getBiddableCampaignCriterion()).getCriterionBid()).getMultiplier();
                    }
                }
        );
    }
}
