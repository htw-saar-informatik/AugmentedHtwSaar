//
//  RestSchnittstelleKontakt.swift
//  AugmentedHTWSaarEsch
//
//  Created by Konrad Zuse on 29.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import Foundation
import Alamofire
import Alamofire_Synchronous

/*
 Schnittstellenfunktion zum versenden umd empfangen von Kontakt bezogene Sachen.
 */
class RestSchnittstelleKontakt{
    
    let config = Config();
    init(){}
    
    /*
     Funktion zum versenden von Nachrichten. Erwartet eine Kontakt-Struct. Aus dieser Struct, wird das JSON-Object gebildet und per Post an die angegeben
     Adresse gesendet
    */
    func neueNachricht(kontakt: Kontakt) -> String
    {
        
        
        let parameters:Parameters = [
            "nachricht": kontakt.nachricht!,
            "empfaenger": [
                "benutzerID": kontakt.empfaenger?.benutzerID,
                "benutzername":kontakt.empfaenger?.benutzername,
                "name": kontakt.empfaenger?.name,
                "vorname":kontakt.empfaenger?.vorname,
                "eMail": kontakt.empfaenger?.eMail
            ],
            "raumnummer": kontakt.raumnummer,
            "sender": [
                "benutzerID" : kontakt.sender?.benutzerID,
                "benutzername" : kontakt.sender?.benutzername,
                "name" : kontakt.sender?.name,
                "vorname": kontakt.sender?.vorname,
                "eMail": kontakt.sender?.eMail
            ],
            "prio": kontakt.prio,
            "betreff" : kontakt.betreff
        ]
        
        let response = Alamofire.request("\(config.url)/kontakt/neu", method: .post, parameters: parameters, encoding: JSONEncoding.default).responseJSON()
       
        let responseDictionary:Dictionary = response.result.value as! Dictionary<String,Any>;
        
        return "\(responseDictionary["Nachricht"]!)";
        
    }
}
