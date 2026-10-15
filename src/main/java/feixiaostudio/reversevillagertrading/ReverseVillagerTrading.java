package feixiaostudio.reversevillagertrading;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseVillagerTrading implements ModInitializer {
	public static final String MOD_ID = "reversevillagertrading";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

	public static TradeOffer reverseOffer(TradeOffer tradeOffer) { // 需要分类讨论
		if (tradeOffer.getSecondBuyItem().isEmpty()) {
			return new TradeOffer(
					tradeOffer.getSellItem(),
					tradeOffer.getSecondBuyItem(),
					tradeOffer.getOriginalFirstBuyItem(),
					tradeOffer.getUses(),
					tradeOffer.getMaxUses(),
					tradeOffer.getMerchantExperience(),
					tradeOffer.getPriceMultiplier(),
					tradeOffer.getDemandBonus()
			);
		} else {
			return new TradeOffer(
					tradeOffer.getOriginalFirstBuyItem(),
					tradeOffer.getSellItem(),
					tradeOffer.getSecondBuyItem(),
					tradeOffer.getUses(),
					tradeOffer.getMaxUses(),
					tradeOffer.getMerchantExperience(),
					tradeOffer.getPriceMultiplier(),
					tradeOffer.getDemandBonus()
				);
		}
	}

	public static void reverseTrading(VillagerEntity villagerEntity) {
		TradeOfferList offerList = villagerEntity.getOffers();
		TradeOfferList reversedOfferList = new TradeOfferList();
		for (TradeOffer offer : offerList) {
			reversedOfferList.add(reverseOffer(offer));
		}
		villagerEntity.setOffers(reversedOfferList);
	}

	public static boolean shouldReverse(MerchantEntity merchantEntity) {
		//String merchantName = merchantEntity.getName().getString();
		//return merchantName.equals("Dinnerbone") || merchantName.equals("Grumm");
		return true;
	}
}