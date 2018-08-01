package com.semantyca.nb.modules.administrator.init;

import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.model.Language;

import java.util.HashMap;
import java.util.Map;

public class ServerConst {

    //it should be moved to property file
    public static Language getLanguage(LanguageCode code) {
        Map<LanguageCode, String> langName = new HashMap<>();
        Language entity = new Language();

        if (code == LanguageCode.ENG) {
            langName.put(LanguageCode.ENG, "English");
            langName.put(LanguageCode.KAZ, "Ангылсша");
            langName.put(LanguageCode.RUS, "Английский");
            langName.put(LanguageCode.BUL, "Англиски");
            langName.put(LanguageCode.CHI, "英语");
            langName.put(LanguageCode.DEU, "Englisch");
            langName.put(LanguageCode.POR, "Inglês");
            langName.put(LanguageCode.SPA, "Inglés");
            langName.put(LanguageCode.BEL, "Английский");
            entity.setOn(true);
        } else if (code == LanguageCode.KAZ) {
            langName.put(LanguageCode.ENG, "Kazakh");
            langName.put(LanguageCode.KAZ, "Қазақша");
            langName.put(LanguageCode.RUS, "Казахский");
            langName.put(LanguageCode.BUL, "Казахски");
            langName.put(LanguageCode.CHI, "哈萨克人");
            langName.put(LanguageCode.DEU, "Kasachisch");
            langName.put(LanguageCode.POR, "Kazakh");
            langName.put(LanguageCode.SPA, "Kazajo");
            langName.put(LanguageCode.BEL, "Казахский");
            entity.setOn(true);
        } else if (code == LanguageCode.RUS) {
            langName.put(LanguageCode.ENG, "Russian");
            langName.put(LanguageCode.KAZ, "Орысша");
            langName.put(LanguageCode.RUS, "Русский");
            langName.put(LanguageCode.BUL, "Руски");
            langName.put(LanguageCode.CHI, "Russian");
            langName.put(LanguageCode.DEU, "Russian");
            langName.put(LanguageCode.POR, "Russo");
            langName.put(LanguageCode.SPA, "Ruso");
            langName.put(LanguageCode.BEL, "Русский");
            entity.setOn(true);
        } else if (code == LanguageCode.BUL) {
            langName.put(LanguageCode.ENG, "Bulgarian");
            langName.put(LanguageCode.KAZ, "Болгар");
            langName.put(LanguageCode.RUS, "Болгарский");
            langName.put(LanguageCode.BUL, "Български");
            langName.put(LanguageCode.CHI, "保加利亚语");
            langName.put(LanguageCode.DEU, "Bulgarisch");
            langName.put(LanguageCode.POR, "Búlgaro");
            langName.put(LanguageCode.SPA, "Búlgaro");
            langName.put(LanguageCode.BEL, "Болгарский");
            entity.setOn(true);
        } else if (code == LanguageCode.CHI) {
            langName.put(LanguageCode.ENG, "Chinese");
            langName.put(LanguageCode.KAZ, "Қытай");
            langName.put(LanguageCode.RUS, "Китайский");
            langName.put(LanguageCode.BUL, "Китаиски");
            langName.put(LanguageCode.CHI, "中文");
            langName.put(LanguageCode.DEU, "Chinesisch");
            langName.put(LanguageCode.POR, "Chinês");
            langName.put(LanguageCode.SPA, "Chino");
            langName.put(LanguageCode.BEL, "Китайский");
        } else if (code == LanguageCode.DEU) {
            langName.put(LanguageCode.ENG, "German");
            langName.put(LanguageCode.KAZ, "Неміс");
            langName.put(LanguageCode.RUS, "Немецкий");
            langName.put(LanguageCode.BUL, "Немски");
            langName.put(LanguageCode.CHI, "德语");
            langName.put(LanguageCode.DEU, "Deutsche");
            langName.put(LanguageCode.POR, "Alemão");
            langName.put(LanguageCode.SPA, "Alemán");
            langName.put(LanguageCode.BEL, "Немецкий");
        } else if (code == LanguageCode.POR) {
            langName.put(LanguageCode.ENG, "Portuguese");
            langName.put(LanguageCode.KAZ, "Португал");
            langName.put(LanguageCode.RUS, "Португальский");
            langName.put(LanguageCode.BUL, "Португалски");
            langName.put(LanguageCode.CHI, "葡萄牙语");
            langName.put(LanguageCode.DEU, "Portugiesisch");
            langName.put(LanguageCode.POR, "Português");
            langName.put(LanguageCode.SPA, "Portugués");
            langName.put(LanguageCode.BEL, "Португальский");
            entity.setOn(true);
        } else if (code == LanguageCode.SPA) {
            langName.put(LanguageCode.ENG, "Spanish");
            langName.put(LanguageCode.KAZ, "Испандық");
            langName.put(LanguageCode.RUS, "Испанский");
            langName.put(LanguageCode.BUL, "Испански");
            langName.put(LanguageCode.CHI, "西班牙语");
            langName.put(LanguageCode.DEU, "Spanisch");
            langName.put(LanguageCode.POR, "Еspanhol");
            langName.put(LanguageCode.SPA, "Español");
            langName.put(LanguageCode.BEL, "Испанский");
            entity.setOn(true);
        } else if (code == LanguageCode.BEL) {
            langName.put(LanguageCode.ENG, "Belarusian");
            langName.put(LanguageCode.KAZ, "Беларусь");
            langName.put(LanguageCode.RUS, "Беларуский");
            langName.put(LanguageCode.BUL, "Беларуски");
            langName.put(LanguageCode.CHI, "白俄罗斯语");
            langName.put(LanguageCode.DEU, "Belarussisch");
            langName.put(LanguageCode.POR, "Bielorrusso");
            langName.put(LanguageCode.SPA, "Bielorruso");
            langName.put(LanguageCode.BEL, "Беларуский");
        }
        entity.setIdentifier(code.toString());
        entity.setLocName(langName);
        entity.setCode(code);
        return entity;
    }
}
