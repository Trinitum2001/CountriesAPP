package com.example.parcial1

import android.os.Parcel
import android.os.Parcelable

data class Country(
    val name: Name,
    val translations: Translations,
    val region: String,
    val population: Long,
    val flags: Flag,
    val maps: Maps
) : Parcelable {
    data class Name(val common: String, val official: String) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(common)
            dest.writeString(official)
        }

        companion object CREATOR : Parcelable.Creator<Name> {
            override fun createFromParcel(parcel: Parcel): Name = Name(parcel)
            override fun newArray(size: Int): Array<Name?> = arrayOfNulls(size)
        }
    }

    data class Translations(val spa: SpanishTranslation) : Parcelable {
        constructor(parcel: Parcel) : this(
            SpanishTranslation(
                parcel.readString()!!,
                parcel.readString()!!
            )
        )

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(spa.official)
            dest.writeString(spa.common)
        }

        companion object CREATOR : Parcelable.Creator<Translations> {
            override fun createFromParcel(parcel: Parcel): Translations = Translations(parcel)
            override fun newArray(size: Int): Array<Translations?> = arrayOfNulls(size)
        }
    }

    data class SpanishTranslation(val official: String, val common: String)

    data class Flag(val png: String) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()!!)

        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(png)
        }

        companion object CREATOR : Parcelable.Creator<Flag> {
            override fun createFromParcel(parcel: Parcel): Flag = Flag(parcel)
            override fun newArray(size: Int): Array<Flag?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Name::class.java.classLoader)!!,
        parcel.readParcelable(Translations::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readParcelable(Flag::class.java.classLoader)!!,
        parcel.readParcelable(Maps::class.java.classLoader)!!
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(name, flags)
        dest.writeParcelable(translations, flags)
        dest.writeString(region)
        dest.writeLong(population)
        dest.writeParcelable(this.flags, flags)
        dest.writeParcelable(this.maps, flags)
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country = Country(parcel)
        override fun newArray(size: Int): Array<Country?> = arrayOfNulls(size)
    }

    data class Maps(val googleMaps: String) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()!!)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(googleMaps)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<Maps> {
            override fun createFromParcel(parcel: Parcel): Maps = Maps(parcel)
            override fun newArray(size: Int): Array<Maps?> = arrayOfNulls(size)
        }
    }
}