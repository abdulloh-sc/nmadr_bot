package uz.imaan;

import uz.imaan.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseV {
    private static DatabaseV instance;

    private Map<Integer, Firma> firmalar = new HashMap<>();
    private Map<Integer, List<Maxsulot>> firmaMaxsulotlar = new HashMap<>();
    private Map<Long, UserSession> sessiyalar = new HashMap<>();
    private Map<Long, List<CartItem>> savatlar = new HashMap<>();
    private Map<Integer, UserProfile> profillar = new HashMap<>();

    private DatabaseV(){
        testDataAdd();
    }

    public static DatabaseV getInstance(){
        if (instance == null){
            instance = new DatabaseV();
        }
        return instance;
    }

    private void testDataAdd(){
        Firma f1 = new Firma(1,"korzinka",null);
        Firma f2 = new Firma(2,"hamidani_dokoni",null);
        firmalar.put(1,f1);
        firmalar.put(2,f2);

        List<Maxsulot> f1Maxsulotlar = new ArrayList<>();
        f1Maxsulotlar.add(new Maxsulot(101,1,"pepsi 0.5L",8000,null));
        f1Maxsulotlar.add(new Maxsulot(102,1,"pepsi 2L",20000,null));
        firmaMaxsulotlar.put(1, f1Maxsulotlar);

        List<Maxsulot> f2Maxsulotlar = new ArrayList<>();
        f1Maxsulotlar.add(new Maxsulot(201,1,"pepsi 1.75L",15000,null));
        firmaMaxsulotlar.put(2,  f2Maxsulotlar);
    }

    public List<Firma> hamaFirmalar(){
        return new ArrayList<>(firmalar.values());
    }

    public Firma firmaTop(int id){
        return firmalar.get(id);
    }

    public List<Maxsulot> firmaMaxsulot(int firmaId){
        return firmaMaxsulotlar.getOrDefault(firmaId, new ArrayList<>());
    }

    public Maxsulot maxsulotTop(int firmaId, int maxsulotId){
        for (Maxsulot m : firmaMaxsulot(firmaId)){
            if (m.getId() == maxsulotId){
                return m;
            }
        }
        return null;
    }

    /*public UserSession sessiyaTop(long chatId){
        return sessiyalar.computeIfAbsent(chatId,k -> new UserSession());
    }*/










}
