package net.houwing.service;

import java.util.List;

public interface Bereken {
//public interface Calculator <T extends Number>{

    public List<String> setFormule(int pos1, int pos2, int pos3, List<String> formuleIn);

    public String berekenResultaat(List<String> inputUser);

}
