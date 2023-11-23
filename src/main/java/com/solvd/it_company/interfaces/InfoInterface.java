package com.solvd.it_company.interfaces;


import com.solvd.it_company.exceptions.SizeOfTeamSmallException;

public interface InfoInterface {
    String getInfo() throws SizeOfTeamSmallException;
}
