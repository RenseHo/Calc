package net.houwing.service;

import java.util.List;

public interface Bereken {
//public interface Calculator <T extends Number>{

    List<String> setFormule(int pos1, int pos2, int pos3, List<String> formuleIn);

    //List<String> berekenFuncties(List<String> inputUser, Character[] checkFunctions);

    String berekenResultaat(List<String> inputStrings, String inputUser);

}
