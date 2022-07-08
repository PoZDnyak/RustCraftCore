package ru.pozdnyak.rustcore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    //Ресурсы
    public static boolean isResourceCoal(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Уголь");
    }
    public static boolean isResourceCloth(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Ткань");
    }
    public static boolean isResourceExplosives(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Взрывчатое Вещество");
    }
    public static boolean isResourceAnimalFat(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Животный Жир");
    }
    public static boolean isResourceGunPowder(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Порох");
    }
    public static boolean isResourceHQMOre(ItemStack item){
        if(!item.getType().equals(Material.DIAMOND))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Руда Высокого Качества");
    }
    public static boolean isResourceLeather(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Кожа");
    }
    public static boolean isResourceLowGradeFuel(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Топливо Низкого Качества");
    }
    public static boolean isResourceMetalFragments(ItemStack item){
        if(!item.getType().equals(Material.IRON_INGOT))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Металлические Фрагменты");
    }
    public static boolean isResourceMetalOre(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Железная Руда");
    }
    public static boolean isResourceHQM(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Металл Высокого Качества");
    }
    public static boolean isResourceStone(ItemStack item){
        if(!item.getType().equals(Material.SLIME_BALL))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Камень");
    }
    public static boolean isResourceSulfurOre(ItemStack item){
        if(!item.getType().equals(Material.GOLD_ORE))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Серная Руда");
    }
    public static boolean isResourceSulfur(ItemStack item){
        if(!item.getType().equals(Material.GOLD_INGOT))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Сера");
    }
    public static boolean isResourceTargetingComputer(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Компьютер");
    }
    public static boolean isResourceWood(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Дерево");
    }
    public static boolean isResourceScrap(ItemStack item){
        if(!item.getType().equals(Material.REDSTONE))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Металлолом");
    }
    public static ItemStack giveResourceCloth(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Ткань");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceExplosives(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Взрывчатое Вещество");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceAnimalFat(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Животный Жир");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceGunPowder(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Порох");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceHQMOre(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Руда Высокого Качества");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceLeather(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Кожа");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceLowGradeFuel(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Топливо Низкого Качества");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceMetalFragments(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металлические Фрагменты");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceMetalOre(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металлическая Руда");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceHQM(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металл Высокого Качества");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceStone(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Камни");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceSulfurOre(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Серная Руда");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceSulfur(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Сера");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceTargetingComputer(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Компьютер");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceWood(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Дерево");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveResourceScrap(int amount){
        ItemStack wood = new ItemStack(Material.REDSTONE,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металлолом");
        wood.setItemMeta(meta);
        return wood;
    }

    //Компоненты
    public static boolean isComponentGears(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Шестеренки");
    }
    public static boolean isComponentMetalBlade(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Металлическое Лезвие");
    }
    public static boolean isComponentMetalPipe(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Металлическая Труба");
    }
    public static boolean isComponentMetalSpring(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Пружины");
    }
    public static boolean isComponentRifleBody(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Корпус Винтовки");
    }
    public static boolean isComponentRoadSigns(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Дорожные Знаки");
    }
    public static boolean isComponentRope(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Веревка");
    }
    public static boolean isComponentSewingKit(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Набор Для Шитья");
    }
    public static boolean isComponentSMGBody(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Корпус Пистолет-Пулемёта");
    }
    public static boolean isComponentSemiAutomaticBody(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Корпус Полуавтомата");
    }
    public static ItemStack giveComponentGears(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Шестеренки");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentMetalBlade(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металлическое лезвие");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentMetalPipe(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Металлическая Труба");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentMetalSpring(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Пружины");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentRifleBody(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Корпус Винтовки");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentRoadSigns(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Дорожные Знаки");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentRope(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Веревка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentSewingKit(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Набор Для Шитья");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentSMGBody(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Корпус Пистолет-Пулемёта");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveComponentSemiAutomaticBody(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Корпус Полуавтомата");
        wood.setItemMeta(meta);
        return wood;
    }

    //Патроны
    public static boolean isAmmoPistolBullet(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Пистолетные Патроны");
    }
    public static boolean isAmmo556RifleAmmo(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Патрон 5.56-мм");
    }
    public static boolean isAmmoRocket(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Ракета");
    }
    public static boolean isAmmoWoodenArrow(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Стрела");
    }
    public static ItemStack giveAmmoPistolBullet(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Пистолетные Патроны");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveAmmo556RifleAmmo(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Патрон 5.56-мм");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveAmmoRocket(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Ракета");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveAmmoWoodenArrow(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Стрела");
        wood.setItemMeta(meta);
        return wood;
    }

    //Разное
    public static boolean isMiscDoorKey(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Дверной Ключ");
    }
    public static ItemStack giveMiscDoorKey(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Дверной Ключ");
        wood.setItemMeta(meta);
        return wood;
    }

    //Медицина
    public static boolean isMedicalBandage(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Бинт");
    }
    public static boolean isMedicalMedkit(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Большая Аптечка");
    }
    public static boolean isMedicalSyringe(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Шприц");
    }
    public static ItemStack giveMedicalBandage(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Бинт");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveMedicalMedkit(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Большая Аптечка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveMedicalSyringe(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Шприц");
        wood.setItemMeta(meta);
        return wood;
    }

    //Tools
    public static boolean isToolsSalvagedAxe(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Самодельный Топор");
    }
    public static boolean isToolsSatchel(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Сумка С Зарядом");
    }
    public static boolean isToolsTimedExplosiveCharge(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("С4");
    }
    public static boolean isToolsHammer(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Киянка");
    }
    public static boolean isToolsHatcher(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Топор");
    }
    public static boolean isToolsSalvagedIcepick(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Самодельный Ледоруб");
    }
    public static boolean isToolsPickAxe(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Кирка");
    }
    public static boolean isToolsRock(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Камень");
    }
    public static boolean isToolsStonePickAxe(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Каменная Кирка");
    }
    public static boolean isToolsStoneHatchet(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Каменный Топор");
    }
    public static boolean isToolsSupplySignal(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Сигнальная Граната");
    }
    public static ItemStack giveToolsSalvagedAxe(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Самодельный Топор");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsSatchel(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Сумка С Зарядом");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsTimedExplosiveCharge(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("С4");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsHammer(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Киянка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsHatchet(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Топор");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsSalvagedIcepick(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Самодельный Ледоруб");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsPickAxe(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Кирка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsRock(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Камень");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsStonePickAxe(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Каменная Кирка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsStoneHatchet(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Каменный Топор");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveToolsSupplySignal(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Сигнальная Граната");
        wood.setItemMeta(meta);
        return wood;
    }

    //Clothing
    public static boolean isClothingBoneArmor(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Жилет Из Костей");
    }
    public static boolean isClothingBucketHelmet(ItemStack item){
        if(!item.getType().equals(Material.HAY_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Шлем Из Ведра");
    }

    public static ItemStack giveClothingBoneArmor(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Жилет Из Костей");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveClothingBucketHelmet(int amount){
        ItemStack wood = new ItemStack(Material.HAY_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Шлем Из Ведра");
        wood.setItemMeta(meta);
        return wood;
    }


    //ADDITIONS
    public static boolean isAdditionsBarrelBlue(ItemStack item){
        if(!item.getType().equals(Material.LAPIS_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Синяя Бочка");
    }
    public static boolean isAdditionsBarrelRed(ItemStack item){
        if(!item.getType().equals(Material.REDSTONE_BLOCK))return false;
        if(!item.hasItemMeta())return false;
        if(!item.getItemMeta().hasDisplayName())return false;
        return item.getItemMeta().getDisplayName().equals("Красная Бочка");
    }

    public static ItemStack giveAdditionsBarrelBlue(int amount){
        ItemStack wood = new ItemStack(Material.LAPIS_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Синяя Бочка");
        wood.setItemMeta(meta);
        return wood;
    }
    public static ItemStack giveAdditionsBarrelRed(int amount){
        ItemStack wood = new ItemStack(Material.REDSTONE_BLOCK,amount);
        ItemMeta meta = wood.getItemMeta();
        meta.setDisplayName("Красная Бочка");
        wood.setItemMeta(meta);
        return wood;
    }

}
