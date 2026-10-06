### Drawdown Business Event

#### Drawdown at Expiry - FX Flexible Forward

Flexible forward
- FX flexible (window) forward `87654321` between `partyB` (Client, the buyer) and `partyA` (BNP Paribas S.A.) to buy `10,000,000 EUR` against USD at `1.375 USD/EUR`, executable between `2011-09-30` and `2012-12-30`.

Expiry
- The residual `10,000,000 EUR` is executed automatically on the expiry date `2012-12-30`, resulting in:
- Original flexible forward `87654321` closed, with a notional of `0 EUR`.
- New FX forward `87654321-EXP` for `10,000,000 EUR` at `1.375 USD/EUR`, settling on `2013-01-02`.
