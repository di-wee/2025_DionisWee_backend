package nus.iss.apis;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nus.iss.models.CoinChangeRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/coinChange")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinChangeResource{
    private static final Logger logger = Logger.getLogger(CoinChangeResource.class.getName());



    @POST
    public Response changeCoin(CoinChangeRequest request) {
        try {
            float targetAmount = request.getTargetAmount();
            List<Double> coins = request.getDenominations();

            if(targetAmount <0 || targetAmount >= 10000.00) {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity("Not Acceptable")
                        .build();
            }

            //scaling targetAmount and coins up by 100, getting rid of decimals
            int scale = 100;
            int scaledAmount = (int)Math.round(targetAmount * scale);
            List<Integer> scaledCoins = coins.stream()
                    .map(coin -> (int)Math.round(coin * scale))
                    .collect(Collectors.toList());

            //initialising array for dynamic programming
            int[] dp = new int[scaledAmount + 1];
            int[] prev = new int[scaledAmount + 1];

            //all amounts are initially infinity, except for dp[0] which takes 0 coins
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            for (int i =1; i<= scaledAmount; i++) {
                for (int coin: scaledCoins) {
                    //checking if its a reachable case
                    if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                        //to +1 coin to the reachable use case if fulfilled if con
                        if (dp[i] > dp[i - coin] + 1) {
                            dp[i] = dp[i - coin] + 1;
                            //storing the val of coin in prev array for tracking
                            prev[i] = coin;
                        }
                    }
                }
            }
            //if at the end of the for loop and  no coins are able to make up the targed amt.
            if (dp[scaledAmount] == Integer.MAX_VALUE) {
                return Response.ok(new ArrayList<>()).build(); //return empty array list cos no combi fulfilled

            }

            //working backwards to figure out min coin needed
            List<Double> result = new ArrayList<>();
            int curr = scaledAmount;
            while (curr > 0) {
                //working from the back to find out the coin that is used
                int coinUsed = prev[curr];
                //revert back to original scaling
                result.add(coinUsed/ 100.0);
                curr -= coinUsed;
            }

            Collections.sort(result);

            return Response.ok(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error: " + e.getMessage());
            return Response.serverError().entity("Error: "+ e.getMessage()).build();
        }
    }


}
