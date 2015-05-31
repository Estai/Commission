package com.epam.manager;

import java.util.*;

public class LocaleManager {
    private HashMap<String,Locale> locales;
    private static final LocaleManager MANAGER=new LocaleManager();

    private LocaleManager(){
        locales= new HashMap<>();
        locales.put("English",Locale.ENGLISH);
        locales.put("Русский", Locale.forLanguageTag("ru"));
    }

    public HashMap<String, Locale> getLocales() {
        return locales;
    }


    public Locale setLocale(String locale){
        if(locale!=null){
            Iterator<Map.Entry<String, Locale>> iterator = locales.entrySet().iterator();
            while(iterator.hasNext())
            {Locale localeMap = iterator.next().getValue();
                if(localeMap.toLanguageTag().equalsIgnoreCase(locale))
                    return localeMap;
            }
        }
        return Locale.forLanguageTag("ru");
    }
    public static LocaleManager getInstance(){
            return MANAGER;
    }
}