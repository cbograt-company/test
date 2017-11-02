package com.microsoft.bingads.v11.api.test.entities.ad_group_product_partition.write;

import com.microsoft.bingads.v11.api.test.entities.ad_group_product_partition.BulkAdGroupProductPartitionTest;
import com.microsoft.bingads.v11.bulk.entities.BulkAdGroupProductPartition;
import com.microsoft.bingads.v11.campaignmanagement.AdGroupCriterion;
import com.microsoft.bingads.v11.campaignmanagement.BiddableAdGroupCriterion;
import com.microsoft.bingads.v11.campaignmanagement.NegativeAdGroupCriterion;
import com.microsoft.bingads.v11.campaignmanagement.ProductPartition;
import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;

import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class WriteIdTest extends BulkAdGroupProductPartitionTest {
    @Parameterized.Parameter(value = 1)
    public Long propertyValue;

    @Parameterized.Parameters
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
    public void testWriteExcluded() {
        testWriteProperty(
                "Id",
                datum,
                propertyValue,
                new BiConsumer<BulkAdGroupProductPartition, Long>() {
                    @Override
                    public void accept(BulkAdGroupProductPartition c, Long v) {
                        ProductPartition criterion = new ProductPartition();

                        AdGroupCriterion adGroupCriterion = new NegativeAdGroupCriterion();

                        adGroupCriterion.setCriterion(criterion);

                        c.setAdGroupCriterion(adGroupCriterion);

                        c.getAdGroupCriterion().setId(v);
                    }
                }
        );
    }

    @Test
    public void testWriteNotExcluded() {
        testWriteProperty(
                "Id",
                datum,
                propertyValue,
                new BiConsumer<BulkAdGroupProductPartition, Long>() {
                    @Override
                    public void accept(BulkAdGroupProductPartition c, Long v) {
                        ProductPartition criterion = new ProductPartition();

                        AdGroupCriterion adGroupCriterion = new BiddableAdGroupCriterion();

                        adGroupCriterion.setCriterion(criterion);

                        c.setAdGroupCriterion(adGroupCriterion);

                        c.getAdGroupCriterion().setId(v);
                    }
                }
        );
    }
}
