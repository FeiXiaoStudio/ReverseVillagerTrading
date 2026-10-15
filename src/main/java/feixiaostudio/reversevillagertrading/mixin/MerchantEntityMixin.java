package feixiaostudio.reversevillagertrading.mixin;

import feixiaostudio.reversevillagertrading.ReverseVillagerTrading;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MerchantEntity.class)
public abstract class MerchantEntityMixin {
    @Redirect(
            method = "fillRecipesFromPool",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/village/TradeOfferList;add(Ljava/lang/Object;)Z"
            )
    )
    private boolean reversevillagertrading$reverseNewOffers(TradeOfferList recipeList, Object tradeOffer) {
        TradeOffer newOffer;
        if (ReverseVillagerTrading.shouldReverse((MerchantEntity) (Object) this)) {
            newOffer = ReverseVillagerTrading.reverseOffer((TradeOffer) tradeOffer);
        } else {
            newOffer = (TradeOffer) tradeOffer;
        }
        return recipeList.add(newOffer);
    }
}
