//
//  User.swift
//  AugmentedRealityHTWSaar
//
//  Created by Marco Becker on 18.07.18.
//  Copyright © 2018 AugmentedReality. All rights reserved.
//

import Foundation
/*
 Struct zum anzeigen der Sprechzeiten.
 */
struct Sprechzeiten: Codable
{
    var raumnummer: Int?
    var benutzerID: User?
    var montag: String?
    var dienstag:String?
    var mittwoch:String?
    var donnerstag:String?
    var freitag:String?
    var nachricht:String?
}
