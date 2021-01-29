package dev.bitsnthings.mc.eitems.bow;

import dev.bitsnthings.mc.eitems.EnderItems;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.permissions.Permission;
import org.bukkit.NamespacedKey;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class EnderBow {
  public static final NamespacedKey EBOW_KEY = new NamespacedKey(EnderItems.getInstance(), "enderbow");
  public static final NamespacedKey EBOW_UPGRADE_KEY = new NamespacedKey(EnderItems.getInstance(), "enderbowupgrade");
  public static final String USE_PERM = "enderitems.bow.use";
  public static final String GIVE_SELF_PERM = "enderitems.bow.give.self";
  public static final String GIVE_OTHERS_PERM = "enderitems.bow.give.others";
  public static ArrayList<Permission> perms = new ArrayList<>();

  public static final List<String> ebowSynonyms = Arrays.asList(new String[] {"bow","ebow","enderbow","ender_bow"});

  private EnderBow() {}

  public static ItemStack createEbow() {
    ItemStack ebow = new ItemStack(Material.BOW);
    ItemMeta meta = ebow.getItemMeta();
    meta.setDisplayName(ChatColor.DARK_PURPLE + "Ender Bow");
    meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
    //meta.setLore(Arrays.asList("haha"));
    ebow.setItemMeta(meta);
    return ebow;
  }

  public static boolean isEbow(ItemStack stack) {
    if (stack == null || stack.getType() != Material.BOW || !stack.hasItemMeta() || !stack.getItemMeta().hasDisplayName()) return false;
    else if (stack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Ender Bow")) return true;
    else return false;
  }

  public static void registerPermissions() {
    perms.add(new Permission(USE_PERM, "Allows player to use enderbow", PermissionDefault.TRUE));
    perms.add(new Permission(GIVE_SELF_PERM, "Allows player give themselves an enderbow", PermissionDefault.OP));
    perms.add(new Permission(GIVE_OTHERS_PERM, "Allows player give others an enderbow", PermissionDefault.OP));
  }

  public static boolean registerEbowRecipes() {
    ItemStack ebow = createEbow();
    ShapedRecipe ebowRecipe = new ShapedRecipe(EBOW_KEY, ebow);
    ebowRecipe.shape(
      "GKE",
      "GEK",
      "GKE"
    );
    ebowRecipe.setIngredient('G', Material.STRING);
    ebowRecipe.setIngredient('K', Material.STICK);
    ebowRecipe.setIngredient('E', Material.ENDER_PEARL);

    ShapedRecipe bowUpgradeRecipe = new ShapedRecipe(EBOW_UPGRADE_KEY, ebow);
    bowUpgradeRecipe.shape(
      "-E-",
      "EBE",
      "-E-"
    );
    bowUpgradeRecipe.setIngredient('B', Material.BOW);
    bowUpgradeRecipe.setIngredient('E', Material.ENDER_PEARL);

    boolean recipeSucc = Bukkit.addRecipe(ebowRecipe);
    boolean upgradeSucc = Bukkit.addRecipe(bowUpgradeRecipe);

    if (recipeSucc && upgradeSucc) return true;
    else return false;
  }
  public static boolean unregisterEbowRecipes() {
    boolean recipeSucc = Bukkit.removeRecipe(EBOW_KEY);
    boolean upgradeSucc = Bukkit.removeRecipe(EBOW_UPGRADE_KEY);
    if (recipeSucc && upgradeSucc) return true;
    else return false;
  }
}
