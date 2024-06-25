package com.houseclay.zebra.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ObjectMapperUtils {

    public String extractList(ArrayList<String> inputList)
    {
        StringBuilder SBString = new StringBuilder();
        for(String str : inputList) {
            SBString.append(str+"-");
        }
        return SBString.substring(0,SBString.length()-1);
    }

    /*
    public String extractPreferredLocationfromList(ArrayList<String> preferredLocations){
        StringBuilder preferredLocString = new StringBuilder();
        for(String str : preferredLocations) {
            preferredLocString.append(str+"-");
        }
        return preferredLocString.substring(0,preferredLocString.length()-1);
    }

    public String extractAssetConfigurationfromList(ArrayList<String> assetConfigurations){
        StringBuilder assetConfigurationsString = new StringBuilder();
        for(String str : assetConfigurations) {
            assetConfigurationsString.append(str+"-");
        }
        return assetConfigurationsString.substring(0,assetConfigurationsString.length()-1);
    }
*/

}
