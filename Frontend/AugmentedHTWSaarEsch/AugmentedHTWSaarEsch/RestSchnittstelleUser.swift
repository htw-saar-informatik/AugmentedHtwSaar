//
//  RestSchnittstelleUser.swift
//  AugmentedRealityHTWSaar
//
//  Created by Konrad Zuse on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import Foundation
import Alamofire
import Alamofire_Synchronous

/*
 Schnittstellenfunktion für alle User bezogene Aktionen. Aufruf des Servers per HTTP
 */
class RestSchnittStelleUser
{
    let config = Config();
    init(){}
    
    /*
     Funktion zum einloggen eines Benutzers. Erwartet eine User Struct
     Erhält als Antwort beim erfolgreichem Einloggen das vervollständigte User Obejct
     Sollte benutzer sich nicht eingeloggt können, erhält er als Antwort die Daten, die er vorher eingegeben hat.
     Wird noch auf Post Methode umgeschtellt.
    */
    func login(user:User) -> User
    {
        

        let response = Alamofire.request("\(config.url)/user/login/benutzername/\(user.benutzername!)/passwort/\(user.passwort!)").responseJSON()
        let decoder = JSONDecoder()
        let user = try? decoder.decode(User.self, from: response.data!)
        return user!
    }
    
    /*
     Funktion zum anlegen eines neuen Benutzers.
     Bekommt als parameter den Benutzername, die Email, das Passwort den Name, den Vorname und die rollenbezeichnung
     Als Antwort wird zurück gegeben, ob es erfolgreich war oder nicht.
     Aus den Variablen wird ein JSON Object erstellt, das mit dem HTTP Aufruf mitgesendet wird.
    */
    func newUser(benutzerID:String, email:String, passwort:String, name:String, vorname:String, rollenbezeichnung:String) -> String{
        
        let parameter: [String: Any] = [
            "benutzername": "\(benutzerID)",
            "eMail": "\(email)",
            "passwort": "\(passwort)",
            "name": "\(name)",
            "vorname": "\(vorname)",
            "rollenBezeichnung" : "\(rollenbezeichnung)"
        ]
        let response = Alamofire.request("\(config.url)/user/register", method: .post, parameters: parameter, encoding: JSONEncoding.default).responseJSON()
        let responseDictionary:Dictionary = response.result.value as! Dictionary<String,Any>;
        
        return "\(responseDictionary["Registrierung"]!)";
                
    }
}
