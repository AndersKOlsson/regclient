import twirl

twirl.manifest(
    twirl.Table(
        description="The table contains the events that occur during the job run",
        schema=twirl.Schema(
            [
                twirl.Column("float_val", twirl.Float()),
                twirl.Column("int_val", twirl.Integer()),
                twirl.Column("timestamp_val", twirl.Timestamp(unit="us", tz="UTC")),
                twirl.Column("datetime_val", twirl.TimestampWithoutZone(unit="us")),
                twirl.Column("string_val", twirl.String()),
                twirl.Column("date_val", twirl.Date()),
                twirl.Column("time_val", twirl.Time(unit="us")),
                twirl.Column("list_val", twirl.Array(value_type=twirl.Integer())),
                twirl.Column("dict_val", twirl.Struct([("a", twirl.Integer())])),
                twirl.Column("json_val", twirl.Json()),
                twirl.Column("numeric_val", twirl.Decimal128(precision=38, scale=9)),
                twirl.Column("bignumeric_val", twirl.Decimal256(precision=76, scale=38)),
            ]
            ),
        trigger_conditions=twirl.Static(),
        job=twirl.PythonJob(update_method=twirl.UpdateMethod.REPLACE),
    )
)
