//
//  User.swift
//  AugmentedHTWSaarEsch
//
//  Created by Konrad Zuse on 13.08.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import Foundation

struct User: Codable{
    
    var benutzerID:Int?
    var benutzername:String?
    var passwort:String?
    var eMail:String?
    var name:String?
    var vorname:String?
    var rollenID:Int?
    var rollenBezeichnung:String?
    
}
