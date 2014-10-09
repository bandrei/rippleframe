package com.septacore.ripple.preprocess.algos;

import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.types.PPType;

// Both, the "GeoIP.data" database and 'com' folder need to be in this folder
// unless they are changed here.
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.Country;
import com.septacore.ripple.preprocess.types.PPTypeString;

import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Get location.
 */
public class Location extends PPAppBase {
    public Location() {
        super(new PPTypeString(), new PPType[] {new PPTypeString()});
    }
    // Given an IP address the function will return the country of origin for
    // the IP.
    // The function can easily be extended to return the city as well but I felt
    // this may be too detailed at this point in time.
    // The database and api are from maxmind.
    private String getLocation(String ip) {

        LookupService lookupservice = null;

        // Assigns database to the lookupservice. Throws IOException.
        try {
            // Uses local database.
            lookupservice = new LookupService("GeoIP.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InetAddress ipAddress = null;
        // Assigns the ip given to the InetAddress format required for the
        // lookup function. Throws UnknownHostException.
        try {
            ipAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Looks up the IP in the database to find the country the host is
        // residing in.
        Country hostCountry = lookupservice.getCountry(ipAddress);
        String name = hostCountry.getName();

        return name;

    }
    @Override
    public Object applyPreprocessor(Object[] argVals) {
        return (Object) getLocation((String)argVals[0]);
    }
}
