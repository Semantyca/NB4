package com.semantyca.nb.localization;

import com.semantyca.nb.localization.constants.LanguageCode;

import java.util.HashMap;

public class Vocabulary {
    public HashMap<String, Sentence> words;

    public Vocabulary() {


    }

    public String getWord(String keyword, LanguageCode lang) {
        try {
            Sentence sent = words.get(keyword);
            if (sent == null) {
                //logger.warning("Translation of word \"" + keyword + "\" to " + lang + ", has not found in vocabulary");
                return keyword;
            } else {
                return sent.words.get(lang);
            }
        } catch (Exception e) {
            return keyword;
        }
    }

}
