package com.microsoft.bingads.examples.v11;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.microsoft.bingads.*;
import static com.microsoft.bingads.examples.v11.ExampleBase.outputCampaignsWithPartialErrors;
import com.microsoft.bingads.v11.campaignmanagement.*;

public class RemarketingLists extends ExampleBase {

    static AuthorizationData authorizationData;
    static ServiceClient<ICampaignManagementService> CampaignService; 
    
    public static void main(java.lang.String[] args) {
   	 
        try
        {
            authorizationData = new AuthorizationData();
            authorizationData.setDeveloperToken(DeveloperToken);
            authorizationData.setAuthentication(new PasswordAuthentication(UserName, Password));
            authorizationData.setCustomerId(CustomerId);
            authorizationData.setAccountId(AccountId);
	         
            CampaignService = new ServiceClient<ICampaignManagementService>(
                    authorizationData, 
                    API_ENVIRONMENT,
                    ICampaignManagementService.class);

            // To discover all remarketing lists that the user can associate with ad groups in the current account (per CustomerAccountId header), 
            // set RemarketingListIds to null when calling the GetRemarketingLists operation.

            ArrayList<AudienceType> audienceType = new ArrayList<AudienceType>();
            audienceType.add(AudienceType.REMARKETING_LIST);
            ArrayOfAudience remarketingLists = getAudiencesByIds(null, audienceType).getAudiences();

            // You must already have at least one remarketing list for the remainder of this example. 

            if (remarketingLists.getAudiences()== null || remarketingLists.getAudiences().size() < 1)
            {
                outputStatusMessage("You do not have any remarketing lists that the current user can associate with ad groups.\n");
                return;
            }
                
            // Add an ad group in a campaign. The ad group will later be associated with remarketing lists. 

            ArrayOfCampaign campaigns = new ArrayOfCampaign();
            Campaign campaign = new Campaign();
            campaign.setName("Summer Shoes " + System.currentTimeMillis());
            campaign.setDescription("Summer shoes line.");
            campaign.setBudgetType(BudgetLimitType.DAILY_BUDGET_STANDARD);
            campaign.setDailyBudget(50.00);
            campaign.setTimeZone("PacificTimeUSCanadaTijuana");
            campaigns.getCampaigns().add(campaign);
            
            ArrayOfAdGroup adGroups = new ArrayOfAdGroup();
            AdGroup adGroup = new AdGroup();
            adGroup.setName("Women's Red Shoes");
            ArrayList<AdDistribution> adDistribution = new ArrayList<AdDistribution>();
            adDistribution.add(AdDistribution.SEARCH);
            adGroup.setAdDistribution(adDistribution);
            adGroup.setStartDate(null);
            Calendar calendar = Calendar.getInstance();
            adGroup.setEndDate(new com.microsoft.bingads.v11.campaignmanagement.Date());
            adGroup.getEndDate().setDay(31);
            adGroup.getEndDate().setMonth(12);
            adGroup.getEndDate().setYear(calendar.get(Calendar.YEAR));
            Bid searchBid = new Bid();
            searchBid.setAmount(0.09);
            adGroup.setSearchBid(searchBid);
            adGroup.setLanguage("English");

            // Applicable for all remarketing lists that are associated with this ad group. TARGET_AND_BID indicates 
            // that you want to show ads only to people included in the remarketing list, with the option to change
            // the bid amount. Ads in this ad group will only show to people included in the remarketing list.
            adGroup.setRemarketingTargetingSetting(RemarketingTargetingSetting.TARGET_AND_BID);

            adGroups.getAdGroups().add(adGroup);

            
            // Add the campaign, ad group, keywords, and ads

            AddCampaignsResponse addCampaignsResponse = addCampaigns(AccountId, campaigns);
            ArrayOfNullableOflong campaignIds = addCampaignsResponse.getCampaignIds();
            ArrayOfBatchError campaignErrors = addCampaignsResponse.getPartialErrors();
            outputCampaignsWithPartialErrors(campaigns, campaignIds, campaignErrors);
            
            AddAdGroupsResponse addAdGroupsResponse = addAdGroups(campaignIds.getLongs().get(0), adGroups);
            ArrayOfNullableOflong adGroupIds = addAdGroupsResponse.getAdGroupIds();
            ArrayOfBatchError adGroupErrors = addAdGroupsResponse.getPartialErrors();
            outputAdGroupsWithPartialErrors(adGroups, adGroupIds, adGroupErrors);
            

            // If the campaign or ad group add operations failed then we cannot continue this example. 

            if (addAdGroupsResponse.getAdGroupIds() == null || addAdGroupsResponse.getAdGroupIds().getLongs().size() < 1)
            {
                return;
            }

            ArrayOfAdGroupCriterion adGroupRemarketingListAssociations = new ArrayOfAdGroupCriterion();

            // This example associates all of the remarketing lists with the new ad group.

            for (Audience remarketingList : remarketingLists.getAudiences())
            {
                if (remarketingList.getId() != null)
                {
                    BiddableAdGroupCriterion biddableAdGroupCriterion = new BiddableAdGroupCriterion();
                    biddableAdGroupCriterion.setAdGroupId(adGroupIds.getLongs().get(0));
                    AudienceCriterion audienceCriterion = new AudienceCriterion();
                    audienceCriterion.setAudienceId(remarketingList.getId());
                    audienceCriterion.setAudienceType(audienceType);
                    biddableAdGroupCriterion.setCriterion(audienceCriterion);
                    BidMultiplier bidMultiplier = new BidMultiplier();
                    bidMultiplier.setMultiplier(20D);
                    biddableAdGroupCriterion.setCriterionBid(bidMultiplier);
                    biddableAdGroupCriterion.setStatus(AdGroupCriterionStatus.PAUSED);
                                        
                    adGroupRemarketingListAssociations.getAdGroupCriterions().add(biddableAdGroupCriterion);

                    outputStatusMessage("\nAssociating the following remarketing list with the ad group.\n");
                    outputRemarketingList((RemarketingList)remarketingList);
                }
            }
            
            ArrayList<AdGroupCriterionType> criterionType = new ArrayList<AdGroupCriterionType>();
            criterionType.add(AdGroupCriterionType.AUDIENCE);
            
            ArrayList<AdGroupCriterionType> getCriterionType = new ArrayList<AdGroupCriterionType>();
            getCriterionType.add(AdGroupCriterionType.REMARKETING_LIST);

            AddAdGroupCriterionsResponse addAdGroupCriterionsResponse = addAdGroupCriterions(
                    adGroupRemarketingListAssociations,
                    criterionType);

            ArrayOflong adGroupCriterionIds = new ArrayOflong();
            for (java.lang.Long id : addAdGroupCriterionsResponse.getAdGroupCriterionIds().getLongs())
            {
                adGroupCriterionIds.getLongs().add(id);
            }
                        
            GetAdGroupCriterionsByIdsResponse getAdGroupCriterionsByIdsResponse = getAdGroupCriterionsByIds(adGroupIds.getLongs().get(0), 
                            adGroupCriterionIds,
                            getCriterionType);
            
            for (AdGroupCriterion adGroupRemarketingListAssociation : 
                    getAdGroupCriterionsByIdsResponse.getAdGroupCriterions().getAdGroupCriterions())
            {
                outputStatusMessage("\nThe following ad group remarketing list association was added.\n");
                outputAdGroupCriterion(adGroupRemarketingListAssociation);
            }

            // You can store the association IDs which can be used to update or delete associations later. 

            ArrayOfNullableOflong nullableAdGroupCriterionIds = addAdGroupCriterionsResponse.getAdGroupCriterionIds();

            // If the associations were added and retrieved successfully let's practice updating and deleting one of them.

            if (nullableAdGroupCriterionIds.getLongs() != null && nullableAdGroupCriterionIds.getLongs().size() > 0)
            {
                ArrayOfAdGroupCriterion adGroupRemarketingListAssociationsUpdates = new ArrayOfAdGroupCriterion();
                
                BiddableAdGroupCriterion biddableAdGroupCriterion = new BiddableAdGroupCriterion();
                biddableAdGroupCriterion.setAdGroupId(adGroupIds.getLongs().get(0));
                AudienceCriterion audienceCriterion = new AudienceCriterion();
                audienceCriterion.setAudienceType(audienceType);
                biddableAdGroupCriterion.setCriterion(audienceCriterion);
                BidMultiplier bidMultiplier = new BidMultiplier();
                bidMultiplier.setMultiplier(10D);
                biddableAdGroupCriterion.setCriterionBid(bidMultiplier);
                biddableAdGroupCriterion.setId(nullableAdGroupCriterionIds.getLongs().get(0));
                biddableAdGroupCriterion.setStatus(AdGroupCriterionStatus.ACTIVE);
                
                adGroupRemarketingListAssociationsUpdates.getAdGroupCriterions().add(biddableAdGroupCriterion);

                updateAdGroupCriterions(
                        adGroupRemarketingListAssociationsUpdates, 
                        criterionType);

                deleteAdGroupCriterions(
                        adGroupIds.getLongs().get(0), 
                        adGroupCriterionIds, 
                        criterionType);
            }

            // Delete the campaign, ad group, and ad group remarketing list associations that were previously added. 
            // The remarketing lists will not be deleted.
            // You should remove this line if you want to view the added entities in the 
            // Bing Ads web application or another tool.

            ArrayOflong deleteCampaignIds = new ArrayOflong();
            deleteCampaignIds.getLongs().add(campaignIds.getLongs().get(0));
            deleteCampaigns(AccountId, deleteCampaignIds);
            System.out.printf("Deleted CampaignId %d\n", campaignIds.getLongs().get(0));

            outputStatusMessage("Program execution completed\n"); 
         
          // Campaign Management service operations can throw AdApiFaultDetail.
        } catch (AdApiFaultDetail_Exception ex) {
            outputStatusMessage("The operation failed with the following faults:\n");

            for (AdApiError error : ex.getFaultInfo().getErrors().getAdApiErrors())
            {
                outputStatusMessage("AdApiError\n");
                outputStatusMessage(String.format("Code: %d\nError Code: %s\nMessage: %s\n\n", error.getCode(), error.getErrorCode(), error.getMessage()));
            }
        
        // Campaign Management service operations can throw ApiFaultDetail.
        } catch (ApiFaultDetail_Exception ex) {
            outputStatusMessage("The operation failed with the following faults:\n");

            for (BatchError error : ex.getFaultInfo().getBatchErrors().getBatchErrors())
            {
                outputStatusMessage(String.format("BatchError at Index: %d\n", error.getIndex()));
                outputStatusMessage(String.format("Code: %d\nMessage: %s\n\n", error.getCode(), error.getMessage()));
            }

            for (OperationError error : ex.getFaultInfo().getOperationErrors().getOperationErrors())
            {
                outputStatusMessage("OperationError\n");
                outputStatusMessage(String.format("Code: %d\nMessage: %s\n\n", error.getCode(), error.getMessage()));
            }
        } catch (RemoteException ex) {
            outputStatusMessage("Service communication error encountered: ");
            outputStatusMessage(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            outputStatusMessage("Error encountered: ");
            outputStatusMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Adds one or more campaigns to the specified account.

    static AddCampaignsResponse addCampaigns(long accountId, ArrayOfCampaign campaigns) throws RemoteException, Exception
    {
        AddCampaignsRequest request = new AddCampaignsRequest();

        request.setAccountId(accountId);
        request.setCampaigns(campaigns);

        return CampaignService.getService().addCampaigns(request);
    }
     
    // Deletes one or more campaigns from the specified account.

    static void deleteCampaigns(long accountId, ArrayOflong campaignIds) throws RemoteException, Exception
    {
        DeleteCampaignsRequest request = new DeleteCampaignsRequest();

        request.setAccountId(accountId);
        request.setCampaignIds(campaignIds);

        CampaignService.getService().deleteCampaigns(request);
    }
    
    // Adds one or more ad groups to the specified campaign.

    static AddAdGroupsResponse addAdGroups(
            long campaignId, 
            ArrayOfAdGroup adGroups) throws RemoteException, Exception
    {
        AddAdGroupsRequest request = new AddAdGroupsRequest();

        request.setCampaignId(campaignId);
        request.setAdGroups(adGroups);

        return CampaignService.getService().addAdGroups(request);
    }
     
    static GetAudiencesByIdsResponse getAudiencesByIds(
            ArrayOflong audienceIds,
            ArrayList<AudienceType> type) throws RemoteException, Exception
    {
        GetAudiencesByIdsRequest request = new GetAudiencesByIdsRequest();

        request.setAudienceIds(audienceIds);
        request.setType(type);

        return CampaignService.getService().getAudiencesByIds(request);
    }
    
    static AddAdGroupCriterionsResponse addAdGroupCriterions(
            ArrayOfAdGroupCriterion adGroupCriterions,
            ArrayList<AdGroupCriterionType> criterionType) throws RemoteException, Exception
    {
        AddAdGroupCriterionsRequest request = new AddAdGroupCriterionsRequest();

        request.setAdGroupCriterions(adGroupCriterions);
        request.setCriterionType(criterionType);

        return CampaignService.getService().addAdGroupCriterions(request);
    }

    static DeleteAdGroupCriterionsResponse deleteAdGroupCriterions(
        long adGroupId,
        ArrayOflong adGroupCriterionIds,
        ArrayList<AdGroupCriterionType> criterionType) throws RemoteException, Exception
    {
        DeleteAdGroupCriterionsRequest request = new DeleteAdGroupCriterionsRequest();

        request.setAdGroupCriterionIds(adGroupCriterionIds);
        request.setAdGroupId(adGroupId);
        request.setCriterionType(criterionType);

        return CampaignService.getService().deleteAdGroupCriterions(request);
    }

    // Gets the ad group remarketing list associations.

    static GetAdGroupCriterionsByIdsResponse getAdGroupCriterionsByIds(
        long adGroupId,
        ArrayOflong adGroupCriterionIds,
        ArrayList<AdGroupCriterionType> criterionType) throws RemoteException, Exception
    {
        GetAdGroupCriterionsByIdsRequest request = new GetAdGroupCriterionsByIdsRequest();

        request.setAdGroupCriterionIds(adGroupCriterionIds);
        request.setAdGroupId(adGroupId);
        request.setCriterionType(criterionType);

        return CampaignService.getService().getAdGroupCriterionsByIds(request);
    }

    // Updates one or more ad group remarketing list associations.

    static UpdateAdGroupCriterionsResponse updateAdGroupCriterions(
            ArrayOfAdGroupCriterion adGroupRemarketingListAssociations,
            ArrayList<AdGroupCriterionType> criterionType) throws RemoteException, Exception
    {
        UpdateAdGroupCriterionsRequest request = new UpdateAdGroupCriterionsRequest();

        request.setAdGroupCriterions(adGroupRemarketingListAssociations);
        request.setCriterionType(criterionType);

        return CampaignService.getService().updateAdGroupCriterions(request);
    }
 }
