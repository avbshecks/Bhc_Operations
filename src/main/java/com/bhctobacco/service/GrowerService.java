package com.bhctobacco.service;

import com.bhctobacco.enums.Rebate;
import com.bhctobacco.model.Bale;
import com.bhctobacco.model.Debt;
import com.bhctobacco.model.Pallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
@Slf4j
public class GrowerService {
    private static final org.slf4j.Logger growerServiceLogger = org.slf4j.LoggerFactory.getLogger(GrowerService.class);

    /**
     * @param baleList
     * @param growerNumber
     * @return
     */
    public double calculateGross(List<Bale> baleList, String growerNumber) {
        double totalGross = getTotalGross(baleList, growerNumber);
        growerServiceLogger.info("received a gross of amount :{}", totalGross);
        return totalGross;
    }

    /**
     * @param baleList
     * @param growerNumber
     * @return
     */
    public double calculateTax1(List<Bale> baleList, String growerNumber) {
        double totalGross = getTotalGross(baleList, growerNumber);
        growerServiceLogger.info("received a gross of amount :{}", totalGross);
        return totalGross * 0.003;
    }

    /**
     * @param baleList
     * @param growerNumber
     * @return
     */
    private static double getTotalGross(List<Bale> baleList, String growerNumber) {
        double totalGross = 0.0;
        double valueOfBale = 0.0;
        for (Bale bale : baleList) {
            if (bale.getGrower().getGrowerNumber().equalsIgnoreCase(growerNumber)) {
                valueOfBale = bale.getMass() * bale.getPrice();
            }
            totalGross += valueOfBale;
        }

        return totalGross;
    }

    /**
     * @param baleList
     * @param growerNumber
     * @return
     */
    public double calculateTax2(List<Bale> baleList, String growerNumber) {
        double totalGross = calculateGross(baleList, growerNumber);
        double tax_2 = totalGross * 0.015;
        double mass = 0.0;
        for (Bale bale : baleList) {
            if (bale.getGrower().getGrowerNumber().equalsIgnoreCase(growerNumber)) {
                mass += bale.getMass();
            }
        }
        return tax_2 + (mass * 0.02);
    }

    /**
     * @param baleList
     * @return
     */
    public double calculateTax3(List<Bale> baleList) {
        int numBales = baleList.size();
        return numBales * 5.00;
    }

    /**
     * @param baleList
     * @param growerNumber
     * @return
     */
    public double calculateCombinedTaxes(List<Bale> baleList, String growerNumber) {
        double totalGross = calculateGross(baleList, growerNumber);
        double tax1 = calculateTax1(baleList, growerNumber);
        growerServiceLogger.info("received a gross of amount :{}", tax1);
        double tax2 = calculateTax2(baleList, growerNumber);
        growerServiceLogger.info("received a gross of amount :{}", tax2);
        double tax3 = calculateTax3(baleList);
        growerServiceLogger.info("received a gross of amount :{}", tax3);
        double combined = tax1 + tax3 + tax2;
        growerServiceLogger.info("Gross calculated tax is  :{}", combined);
        return totalGross-combined;
    }
    public double calculateDebt(List<Bale> baleList, String growerNumber, Debt debt) {
        double totalGross = calculateGross(baleList, growerNumber);
        double Interest= (debt.getAmount() * (debt.getInterestRate()/100));
        double commission = Interest + debt.getAmount()*0.005;
        double amountAfterInterestAndComm= debt.getAmount()+Interest+commission;
        return totalGross - amountAfterInterestAndComm;
    }

    public double calculateDebtsWithPriority(TreeMap<Integer,Double> debts, List<Bale> baleList, String growerNumber) {
        double totalGross = calculateGross(baleList, growerNumber);
        double commission = 0.0;
//        debts = new TreeMap<>(Comparator.reverseOrder());
//        for (Double value : debts.values()) {
//            double Interest= (value* (debt.getInterestRate()/100));
//            double commission = Interest + debt.getAmount()*0.005;
//            double amountAfterInterestAndComm= debt.getAmount()+Interest+commission;
     //   }
        return totalGross - commission;
    }

    public double calculateRebate(List<Bale> baleList, String growerNumber, Rebate rebate) {
        double totalGross = calculateGross(baleList, growerNumber);
        double totalRebate = 0.0;
        switch (rebate) {
            case REBATE_1:
                double mass = 0.0;
                for (Bale bale : baleList) {
                    if (bale.getGrower().getGrowerNumber().equalsIgnoreCase(growerNumber)) {
                        mass += bale.getMass();
                    }
                }
                totalRebate += (0.05 * mass);

                break;
            case REBATE_2:

                totalRebate = (0.02 * totalGross) + 10.00;
                break;
        }
        return totalRebate;
    }
    public double calculateProcessSale(List<Bale> baleList, String growerNumber, Rebate rebate, Debt debt) {
        double totalGross = calculateGross(baleList, growerNumber);
        double totalRebate = calculateRebate(baleList,growerNumber,rebate);
        double totalTaxes = calculateCombinedTaxes(baleList,growerNumber);
        double totalDebts = calculateDebt(baleList,growerNumber,debt);
        growerServiceLogger.info("Logging total gross: {}", totalGross);
        return totalGross-(totalGross-totalTaxes)+(-totalDebts)+totalRebate;
    }
    public Bale  reworks(List<Bale> baleList)  {
        double allowedMass = 120;
        Bale combined = new Bale();
        for (int i = 0; i < baleList.size(); i++) {
            if ( baleList.size() != 2 || (! baleList.get(0).getGrade().toString().equalsIgnoreCase(baleList.get(1).getGrade().toString())) ||
                   (baleList.get(0).getMass() + baleList.get(1).getMass() > allowedMass)) {
                growerServiceLogger.warn("rejected ", baleList);
                combined.setBarcode(baleList.get(i).getBarcode());
            }
            else
            {
            combined.setMass(baleList.get(0).getMass()+ baleList.get(1).getMass());
            Long barcode = Math.round(100000000 + Math.random() * 90000);
            combined.setBarcode(barcode.toString());
            combined.setPrice((baleList.get(0).getPrice()+baleList.get(1).getPrice())/2);
            combined.setGrade(baleList.get(0).getGrade());
        }
        }
        return combined;
    }
    public Pallet loadPallet(List<Bale> baleList) {
        int maxPalletSize = 25;
        int minPalletSize = 1;
        Pallet pallet = new Pallet();
        Long palletId = Math.round(1000 + Math.random() * 90000);
        List<String> allowableGrades= new ArrayList<>();
        for (int i = 0; i < baleList.size(); i++) {
            if (baleList.size() > maxPalletSize || baleList.size() < minPalletSize && (! baleList.contains(allowableGrades))) {
                //pallet.setPalletNumber(palletId.toString());//reject
            }
            else {
                pallet.setPalletNumber(palletId.toString());
                pallet.setBaleCount(baleList.size());
                pallet.setBales(baleList);
                pallet.setGrades(allowableGrades);

            }
        }
            return pallet;
        }

}


