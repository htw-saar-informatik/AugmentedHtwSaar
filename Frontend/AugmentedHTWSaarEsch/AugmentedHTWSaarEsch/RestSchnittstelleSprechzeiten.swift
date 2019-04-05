//
//  SprechzeitenRestSchnittstelle.swift
//  AugmentedRealityHTWSaar
//
//  Created by Marco Becker on 18.07.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import Foundation
import Alamofire
import Alamofire_Synchronous
/*
 Schnittstellenfunktion zum Verwalten der Sprechzeiten.
 AKtuell wird nur die Sprechzeiten zu dem Professor und dem Raum ausgelesen.
 In nacher zukunft soll noch erweitert werden um die Sprechzeiten ändern zu können
 */
class RestSchnittstelleSprechzeiten
{
    let config = Config();
    init(){}
    
    /*
     Funktion zum aufrufen der Sprechzeiten für einen Professor in einem bestimmten Raum
     Liefert als Antwort eine Struct Sprechzeiten die alle informationen zu den Sprechzeiten beinhaltet.
    */
    func getSprechzeitenZuProfUndRaum(raumNr:Int,benutzerID: Int) -> Sprechzeiten
    {
        let response = Alamofire.request("\(config.url)/sprechzeiten/raumnummer/\(raumNr)/benutzerID/\(benutzerID)").responseData()
        
        let decoder = JSONDecoder()
        var sprechzeiten = try? decoder.decode(Sprechzeiten.self, from: response.data!)
        
        
        return sprechzeiten!;
        
    }
}
