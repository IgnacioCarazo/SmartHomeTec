PGDMP     
                    y            SmartHomeDB    13.2    13.2 /               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16403    SmartHomeDB    DATABASE     q   CREATE DATABASE "SmartHomeDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Latin America.1252';
    DROP DATABASE "SmartHomeDB";
                postgres    false            ?            1259    16493    Admin    TABLE     U   CREATE TABLE public."Admin" (
    email text NOT NULL,
    password text NOT NULL
);
    DROP TABLE public."Admin";
       public         heap    postgres    false            ?            1259    16404    Client    TABLE       CREATE TABLE public."Client" (
    name text NOT NULL,
    "primaryLastName" text NOT NULL,
    "secondaryLastName" text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    continent text NOT NULL,
    country text NOT NULL,
    "deliveryAdresses" text[] NOT NULL
);
    DROP TABLE public."Client";
       public         heap    postgres    false            ?            1259    16664    Control    TABLE     |   CREATE TABLE public."Control" (
    "time" integer NOT NULL,
    date text NOT NULL,
    "SerialNumber" integer NOT NULL
);
    DROP TABLE public."Control";
       public         heap    postgres    false            ?            1259    16425    Device    TABLE     2  CREATE TABLE public."Device" (
    name text NOT NULL,
    "serialNumber" integer NOT NULL,
    "eConsumption" text NOT NULL,
    brand text NOT NULL,
    associated boolean NOT NULL,
    "typeName" text NOT NULL,
    "ownerEmail" text,
    "dniDistributor" integer NOT NULL,
    price integer NOT NULL
);
    DROP TABLE public."Device";
       public         heap    postgres    false            ?            1259    16412 
   DeviceType    TABLE     ?   CREATE TABLE public."DeviceType" (
    name text NOT NULL,
    description text NOT NULL,
    "warrantyTime" integer NOT NULL
);
     DROP TABLE public."DeviceType";
       public         heap    postgres    false            ?            1259    16451    Device_serialNumber_seq    SEQUENCE     ?   ALTER TABLE public."Device" ALTER COLUMN "serialNumber" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Device_serialNumber_seq"
    START WITH 1199
    INCREMENT BY 27
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            ?            1259    16433    Distributor    TABLE     ?   CREATE TABLE public."Distributor" (
    dni integer NOT NULL,
    name text NOT NULL,
    continent text NOT NULL,
    country text NOT NULL
);
 !   DROP TABLE public."Distributor";
       public         heap    postgres    false            ?            1259    16592    Invoice    TABLE     ?   CREATE TABLE public."Invoice" (
    "invoiceNumber" integer NOT NULL,
    "deviceTypeName" text NOT NULL,
    price integer NOT NULL,
    date text NOT NULL
);
    DROP TABLE public."Invoice";
       public         heap    postgres    false            ?            1259    16453    Order    TABLE        CREATE TABLE public."Order" (
    "consecutiveNumberOrder" integer NOT NULL,
    "deviceSerialNumber" integer NOT NULL,
    date text NOT NULL,
    hour text NOT NULL,
    brand text NOT NULL,
    price integer NOT NULL,
    "deviceOwner" text NOT NULL,
    "orderID" integer NOT NULL
);
    DROP TABLE public."Order";
       public         heap    postgres    false            ?            1259    16501    Order_orderID_seq    SEQUENCE     ?   ALTER TABLE public."Order" ALTER COLUMN "orderID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Order_orderID_seq"
    START WITH 2799
    INCREMENT BY 11
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            ?            1259    16656    RegisteredDevice    TABLE     5  CREATE TABLE public."RegisteredDevice" (
    description text NOT NULL,
    consumption text NOT NULL,
    brand text NOT NULL,
    type text NOT NULL,
    "createdDate" text NOT NULL,
    room text NOT NULL,
    "serialNumber" integer NOT NULL,
    "emailOwner" text NOT NULL,
    active boolean NOT NULL
);
 &   DROP TABLE public."RegisteredDevice";
       public         heap    postgres    false            ?            1259    16628    Room    TABLE     V   CREATE TABLE public."Room" (
    name text NOT NULL,
    "userEmail" text NOT NULL
);
    DROP TABLE public."Room";
       public         heap    postgres    false            ?            1259    16610    Warranty    TABLE     ?   CREATE TABLE public."Warranty" (
    "clientName" text NOT NULL,
    "deviceSerialNumber" integer NOT NULL,
    brand text NOT NULL,
    "purchaseDate" text NOT NULL,
    "expireDate" text NOT NULL,
    "deviceTypeName" text NOT NULL
);
    DROP TABLE public."Warranty";
       public         heap    postgres    false            ?          0    16493    Admin 
   TABLE DATA           2   COPY public."Admin" (email, password) FROM stdin;
    public          postgres    false    206   ?8       ?          0    16404    Client 
   TABLE DATA           ?   COPY public."Client" (name, "primaryLastName", "secondaryLastName", email, password, continent, country, "deliveryAdresses") FROM stdin;
    public          postgres    false    200   ?8       ?          0    16664    Control 
   TABLE DATA           A   COPY public."Control" ("time", date, "SerialNumber") FROM stdin;
    public          postgres    false    212   K9       ?          0    16425    Device 
   TABLE DATA           ?   COPY public."Device" (name, "serialNumber", "eConsumption", brand, associated, "typeName", "ownerEmail", "dniDistributor", price) FROM stdin;
    public          postgres    false    202   h9       ?          0    16412 
   DeviceType 
   TABLE DATA           I   COPY public."DeviceType" (name, description, "warrantyTime") FROM stdin;
    public          postgres    false    201   ?9       ?          0    16433    Distributor 
   TABLE DATA           F   COPY public."Distributor" (dni, name, continent, country) FROM stdin;
    public          postgres    false    203   K:       ?          0    16592    Invoice 
   TABLE DATA           S   COPY public."Invoice" ("invoiceNumber", "deviceTypeName", price, date) FROM stdin;
    public          postgres    false    208   ,;       ?          0    16453    Order 
   TABLE DATA           ?   COPY public."Order" ("consecutiveNumberOrder", "deviceSerialNumber", date, hour, brand, price, "deviceOwner", "orderID") FROM stdin;
    public          postgres    false    205   I;       ?          0    16656    RegisteredDevice 
   TABLE DATA           ?   COPY public."RegisteredDevice" (description, consumption, brand, type, "createdDate", room, "serialNumber", "emailOwner", active) FROM stdin;
    public          postgres    false    211   f;       ?          0    16628    Room 
   TABLE DATA           3   COPY public."Room" (name, "userEmail") FROM stdin;
    public          postgres    false    210   ?;       ?          0    16610    Warranty 
   TABLE DATA              COPY public."Warranty" ("clientName", "deviceSerialNumber", brand, "purchaseDate", "expireDate", "deviceTypeName") FROM stdin;
    public          postgres    false    209   ?;                  0    0    Device_serialNumber_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public."Device_serialNumber_seq"', 1766, true);
          public          postgres    false    204                       0    0    Order_orderID_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public."Order_orderID_seq"', 3316, true);
          public          postgres    false    207            b           2606    16500    Admin Admin_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public."Admin"
    ADD CONSTRAINT "Admin_pkey" PRIMARY KEY (email);
 >   ALTER TABLE ONLY public."Admin" DROP CONSTRAINT "Admin_pkey";
       public            postgres    false    206            X           2606    16411    Client Client_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public."Client"
    ADD CONSTRAINT "Client_pkey" PRIMARY KEY (email);
 @   ALTER TABLE ONLY public."Client" DROP CONSTRAINT "Client_pkey";
       public            postgres    false    200            Z           2606    16419    DeviceType DeviceType_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."DeviceType"
    ADD CONSTRAINT "DeviceType_pkey" PRIMARY KEY (name);
 H   ALTER TABLE ONLY public."DeviceType" DROP CONSTRAINT "DeviceType_pkey";
       public            postgres    false    201            \           2606    16432    Device Device_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "Device_pkey" PRIMARY KEY ("serialNumber");
 @   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "Device_pkey";
       public            postgres    false    202            ^           2606    16440    Distributor Distributor_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Distributor"
    ADD CONSTRAINT "Distributor_pkey" PRIMARY KEY (dni);
 J   ALTER TABLE ONLY public."Distributor" DROP CONSTRAINT "Distributor_pkey";
       public            postgres    false    203            d           2606    16599    Invoice Invoice_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "Invoice_pkey" PRIMARY KEY ("invoiceNumber");
 B   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "Invoice_pkey";
       public            postgres    false    208            `           2606    16477    Order Order_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY ("orderID");
 >   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "Order_pkey";
       public            postgres    false    205            h           2606    16635    Room Room_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Room"
    ADD CONSTRAINT "Room_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Room" DROP CONSTRAINT "Room_pkey";
       public            postgres    false    210            f           2606    16617    Warranty Warranty_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Warranty"
    ADD CONSTRAINT "Warranty_pkey" PRIMARY KEY ("deviceSerialNumber");
 D   ALTER TABLE ONLY public."Warranty" DROP CONSTRAINT "Warranty_pkey";
       public            postgres    false    209            l           2606    16483    Order deviceOwner    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "deviceOwner" FOREIGN KEY ("deviceOwner") REFERENCES public."Client"(email) NOT VALID;
 ?   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "deviceOwner";
       public          postgres    false    2904    200    205            k           2606    16478    Order deviceSerialNumber    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "deviceSerialNumber" FOREIGN KEY ("deviceSerialNumber") REFERENCES public."Device"("serialNumber") NOT VALID;
 F   ALTER TABLE ONLY public."Order" DROP CONSTRAINT "deviceSerialNumber";
       public          postgres    false    202    2908    205            o           2606    16618    Warranty deviceSerialNumber    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Warranty"
    ADD CONSTRAINT "deviceSerialNumber" FOREIGN KEY ("deviceSerialNumber") REFERENCES public."Device"("serialNumber");
 I   ALTER TABLE ONLY public."Warranty" DROP CONSTRAINT "deviceSerialNumber";
       public          postgres    false    2908    209    202            n           2606    16605    Invoice deviceTypeName    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "deviceTypeName" FOREIGN KEY ("deviceTypeName") REFERENCES public."DeviceType"(name);
 D   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "deviceTypeName";
       public          postgres    false    2906    208    201            p           2606    16623    Warranty deviceTypeName    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Warranty"
    ADD CONSTRAINT "deviceTypeName" FOREIGN KEY ("deviceTypeName") REFERENCES public."DeviceType"(name);
 E   ALTER TABLE ONLY public."Warranty" DROP CONSTRAINT "deviceTypeName";
       public          postgres    false    209    2906    201            j           2606    16446    Device dniDistributor    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "dniDistributor" FOREIGN KEY ("dniDistributor") REFERENCES public."Distributor"(dni) NOT VALID;
 C   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "dniDistributor";
       public          postgres    false    202    2910    203            m           2606    16600    Invoice invoiceNumber    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Invoice"
    ADD CONSTRAINT "invoiceNumber" FOREIGN KEY ("invoiceNumber") REFERENCES public."Order"("orderID");
 C   ALTER TABLE ONLY public."Invoice" DROP CONSTRAINT "invoiceNumber";
       public          postgres    false    208    205    2912            i           2606    16441    Device typeName    FK CONSTRAINT     ?   ALTER TABLE ONLY public."Device"
    ADD CONSTRAINT "typeName" FOREIGN KEY ("typeName") REFERENCES public."DeviceType"(name) NOT VALID;
 =   ALTER TABLE ONLY public."Device" DROP CONSTRAINT "typeName";
       public          postgres    false    202    2906    201            ?      x?????? ? ?      ?   w   x??H??L??t/M?????/?,J??M,??? ?8d???&f??%??r?p:??e&'r:??$*???J??y
^?ũJ?\? ?h?)#sKK??M??????XT???_????? ?6s      ?      x?????? ? ?      ?   c   x??,???KU?V?u??447??44T?.???t,(?I?L??M,*	 )???0??07?
N?-.?KW?pj13-Pq???0NNcSNC3#c?=... ???      ?   `   x??=? @ᙞ?'0?x'7??b?M?5?s~???l?_?2????0?ɑ?˅A?[}xW	?U|????n7?t????H?s??4?1 ???!      ?   ?   x?u?AO?0??ί?/@Z??r̦
u??????)u??=?????q????????4??s*?S??????ξ??K?`?&??4????`s?w[??o\?S???F????]??;i???0?{?Q=I?1?x9 ?`N??G??S8?o????eN?ZRFW?
?F?z?m?3????u?Ӳ????????M??~???"???R???c?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?     