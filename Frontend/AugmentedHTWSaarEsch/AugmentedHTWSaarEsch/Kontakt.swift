//
//  Kontakt.swift
//  AugmentedHTWSaarEsch
//
//  Created by Marco Becker on 13.09.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

/*
 Struct zum Abbilden einer Kontakt Nachricht.
 */
import Foundation
struct Kontakt: Codable
{
    var raumnummer:Int?
    var sender:User?
    var empfaenger:User?
    var prio:Int?
    var betreff:String?
    var nachricht:String?
}
