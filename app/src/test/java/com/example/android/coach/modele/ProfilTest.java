package com.example.android.coach.modele;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

public class ProfilTest {
    //creation d'un profil
    private Profil profil = new Profil(67, 165, 35, 0);
    //resultatIMG
    private float img = (float)32.2;
    //message
    private String message = "trop élévé";

    @Test
    public void testGetImg() throws Exception {
        assertEquals(img, profil.getImg(), (float)0.1);//à 0.1 près
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals(message, profil.getMessage());
    }
}