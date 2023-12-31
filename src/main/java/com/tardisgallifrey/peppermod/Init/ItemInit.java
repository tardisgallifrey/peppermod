package com.tardisgallifrey.peppermod.Init;

import com.tardisgallifrey.peppermod.PepperModMain;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

//This is the Item Init(ialization) class
//There can be one for Blocks and
// ones for other Entities

public class ItemInit {

    //no constructor in this class

    //Basic DeferredRegister of an Item
    //We defer registry until it's called in
    //minecraft.
    //The Item variable type comes from Minecraft
    //Our variable is ITEMS and is a static var and const
    //We create the deferred registration from a Forge
    //class with a create method requiring the
    //name of the registry, its method and
    //our mod ID
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    PepperModMain.MOD_ID);

    //This is the actual registry and creation of
    //the properties and behavior of the item.
    //Our item variable is a smiley face.
    //It requires running the ITEMS register method
    //which requires the item name (SMILE is a class var,
    // smile is a name for the class var).
    // Item name is the first param and the second
    // is called a supplier.  It is a lambda function,
    // or as I know it, it is a call back anonymous function
    // that creates a new Item instance with new Item Properties.
    // Here, we aren't giving any.
    public static final RegistryObject<Item> PEPPER =
            ITEMS.register("pepper",
                () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.instance)
                        .food( new FoodProperties.Builder()
                                .nutrition(3)
                                .saturationMod(1)
                                .effect(() -> new MobEffectInstance(
                                        MobEffects.WATER_BREATHING,
                                        7200, 1),
                                        0.7F)
                                .build()
                        )
            )); //the .tab(ModcreativeTab.instance) is
                //adding the creative mode tab from below
                //to the properties of our Smiley Face
                //
                //The effect method is specific.
                //It must be:
                // effect( () -> effect_supplier   , probability(float))
                // where effect_supplier is similar to:
                // new effect_instance( effect_type.type, param1...paramN)
                //Water Breathing uses two params, ticks duration, and
                //level WB1, WB2, etc.

    //This is an inner class to ItemInit
    //It allows creation of the
    //CreativeModeTab (what we are extending
    // from), or inheriting all the classes and
    // methods from it.
    //Our constructor inherits the similar params
    //from the parent (super).
    // We are overriding the ItemStack makeIcon method
    // to get it to return our own SMILE object.
    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(PEPPER.get());
        }

        //This creates an instance of the above
        //creative mode tab
        public static final ModCreativeTab instance =
                new ModCreativeTab(CreativeModeTab
                        .TABS.length, "peppermod");
    }
}
